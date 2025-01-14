package com.example.appointmentschedulingservice.service;


import com.api.project2.model.generated.appointmentscheduling.*;


import java.util.Optional;

public interface AppointmentSchedulingService {

    Optional<AppointmentDetails> getAppointmentById(Integer appointmentId);

    CreateAppointmentResponse createAppointment(CreateAppointmentRequest createAppointmentRequest);

    UpdateAppointmentResponse updateAppointment(UpdateAppointmentRequest updateAppointmentRequest, Integer appointmentId);

    DeleteAppointmentResponse deleteAppointment(Integer appointmentId);


}
