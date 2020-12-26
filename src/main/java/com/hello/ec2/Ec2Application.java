package com.hello.ec2;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
@AllArgsConstructor
public class Ec2Application implements ApplicationListener<ApplicationReadyEvent> {

	public static void main(String[] args) {
		SpringApplication.run(Ec2Application.class, args);
	}

	private static String port;
	private static String localHostName;
	private static String localAddress;
	private static String remoteHostName;
	private static String remoteAddress;

	@GetMapping("/whoami")
	public String get() {
		WhoAmIResponse whoAmIResponse = WhoAmIResponse.builder()
				.port(port)
				.localHostName(localHostName)
				.localAddress(localAddress)
				.remoteHostName(remoteHostName)
				.remoteAddress(remoteAddress)
				.build();

		return whoAmIResponse.toString();
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		port = event.getApplicationContext().getEnvironment().getProperty("local.server.port");

		try {
			localHostName = InetAddress.getLocalHost().getHostName();
			localAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}


		remoteHostName = InetAddress.getLoopbackAddress().getHostName();
		remoteAddress = InetAddress.getLoopbackAddress().getHostAddress();
	}

	@lombok.Value
	@lombok.Builder
	@lombok.ToString
	public static class WhoAmIResponse {
		String port;
		String localHostName;
		String localAddress;
		String remoteHostName;
		String remoteAddress;
	}
}
