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
class Ec2ApplicationTests extends Specification {

	@Autowired
	private MockMvc mockMvc

	def "when get is performed then the response has status 200 and content is who am I"() {
		expect: "Status is 200 and the response is who am I"
		mockMvc.perform(MockMvcRequestBuilders.get(URI.create('/whoami')))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
	}

}
