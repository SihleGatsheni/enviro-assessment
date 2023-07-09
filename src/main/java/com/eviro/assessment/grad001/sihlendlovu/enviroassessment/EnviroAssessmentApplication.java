package com.eviro.assessment.grad001.sihlendlovu.enviroassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eviro.assessment.grad001.sihlendlovu.enviroassessment"})
public class EnviroAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnviroAssessmentApplication.class, args);
	}

}
