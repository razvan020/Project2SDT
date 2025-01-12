package com.example.appointmentschedulingservice.service;

import com.api.project2.model.generated.appointmentscheduling.*;
import com.example.appointmentschedulingservice.repository.AppointmentSchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentSchedulingServiceImpl implements AppointmentSchedulingService {

    private final AppointmentSchedulingRepository appointmentSchedulingRepository;

    @Override
    public Optional<AppointmentDetails> getAppointmentById(BigDecimal appointmentId) {
        return Optional.ofNullable(appointmentSchedulingRepository.getAppointmentById(appointmentId));
    }

    @Override
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        return appointmentSchedulingRepository.createAppointment(createAppointmentRequest);
    }

    @Override
    public UpdateAppointmentResponse updateAppointment(UpdateAppointmentRequest updateAppointmentRequest, BigDecimal appointmentId) {
        return appointmentSchedulingRepository.updateAppointment(updateAppointmentRequest, appointmentId);
    }

    @Override
    public DeleteAppointmentResponse deleteAppointment(BigDecimal appointmentId) {
        return appointmentSchedulingRepository.deleteAppointment(appointmentId);
    }
}