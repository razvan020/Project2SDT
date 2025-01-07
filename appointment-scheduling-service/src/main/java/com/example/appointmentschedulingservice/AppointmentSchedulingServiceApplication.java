package com.example.appointmentschedulingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppointmentSchedulingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentSchedulingServiceApplication.class, args);
    }

}
