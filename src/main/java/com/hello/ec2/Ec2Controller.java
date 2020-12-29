package com.hello.ec2;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/ec2")
public class Ec2Controller {

    @NonNull
    private final AmazonEC2 amazonEC2;

    @GetMapping("/instances")
    public List<Instance> get() {
        List<com.amazonaws.services.ec2.model.Instance> reservationInstances = amazonEC2.describeInstances()
                .getReservations()
                .stream()
                .map(Reservation::getInstances)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Instance> instances = reservationInstances
                .stream()
                .map(instance -> Instance.builder()
                        .instanceId(instance.getInstanceId())
                        .publicIp(instance.getPublicIpAddress())
                        .publicDnsName(instance.getPublicDnsName())
                        .privateDnsName(instance.getPrivateDnsName())
                        .instanceState(instance.getState().getName())
                        .build())
                .collect(Collectors.toList());

        log.info("EC2 instances found: {}", instances);
        return instances;
    }

    @lombok.Value
    @lombok.Builder
    public static class Instance {
        String instanceId;
        String publicIp;
        String publicDnsName;
        String privateDnsName;
        String instanceState;
    }

}
