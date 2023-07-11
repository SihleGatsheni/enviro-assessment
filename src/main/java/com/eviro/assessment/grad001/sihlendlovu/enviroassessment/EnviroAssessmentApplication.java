package com.eviro.assessment.grad001.sihlendlovu.enviroassessment;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.contracts.FileParser;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eviro.assessment.grad001.sihlendlovu.enviroassessment"})
public class EnviroAssessmentApplication {

	@Autowired
	private FileParser fileParser; // injecting file parser
	public static void main(String[] args) {
		SpringApplication.run(EnviroAssessmentApplication.class, args);
	}

 	@PostConstruct
    private void parseFile() { //parse file on startup
        try {
            var currentDirectory = System.getProperty("user.dir"); //get current directory
            var filePath = "/"+ currentDirectory + "/wwwroot/1672815113084-GraduateDev_AssessmentCsv_Ref003.csv";
            fileParser.parseCSV(new File(filePath));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
