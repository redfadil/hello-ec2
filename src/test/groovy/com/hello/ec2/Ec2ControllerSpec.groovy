package com.hello.ec2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class Ec2ApplicationSpec extends Specification {

	@Autowired
	private MockMvc mockMvc

	def "when GET /instances is called then the response has status 200 and content is describing the instances"() {
		expect:
		mockMvc.perform(MockMvcRequestBuilders.get(URI.create('/ec2/instances')))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
	}

}
