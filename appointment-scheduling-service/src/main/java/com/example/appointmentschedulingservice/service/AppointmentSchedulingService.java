package com.example.appointmentschedulingservice.service;



import com.api.project2.model.generated.appointmentscheduling.*;

import java.math.BigDecimal;


import java.util.Optional;

public interface AppointmentSchedulingService {

    Optional<AppointmentDetails> getAppointmentById(BigDecimal appointmentId);

    CreateAppointmentResponse createAppointment(CreateAppointmentRequest createAppointmentRequest);

    UpdateAppointmentResponse updateAppointment(UpdateAppointmentRequest updateAppointmentRequest, BigDecimal appointmentId);

    DeleteAppointmentResponse deleteAppointment(BigDecimal appointmentId);

}
