package com.example.project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.project2")
@EntityScan(basePackages = {"com.api.project2.model.generated.patientsrecords"})
@EnableJpaRepositories(basePackages = {"com.example.project2.repository"})
public class PatientsRecordsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsRecordsServiceApplication.class, args);

    }

}
