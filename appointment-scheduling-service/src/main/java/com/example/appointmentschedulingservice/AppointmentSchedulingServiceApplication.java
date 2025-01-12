package com.example.appointmentschedulingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.example.appointmentschedulingservice")
@EntityScan(basePackages = {"com.api.project2.model.generated.appointmentscheduling"})
@EnableJpaRepositories(basePackages = {"com.example.appointmentschedulingservice.repository"})
public class AppointmentSchedulingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentSchedulingServiceApplication.class, args);
    }

}
