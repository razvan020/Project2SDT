package com.example.appointmentschedulingservice.controller;



import com.api.project2.controller.generated.appointmentscheduling.ApiApi;
import com.api.project2.model.generated.appointmentscheduling.*;
import com.example.appointmentschedulingservice.service.AppointmentSchedulingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AppointmentSchedulingController implements ApiApi {

    private final AppointmentSchedulingService appointmentSchedulingService;


    @Override
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@Valid CreateAppointmentRequest createAppointmentRequest) {
        return ResponseEntity.ok(appointmentSchedulingService.createAppointment(createAppointmentRequest));
    }

    @Override
    public ResponseEntity<DeleteAppointmentResponse> deleteAppointment(@NotNull @Valid BigDecimal appointmentId) {
        return ResponseEntity.ok(appointmentSchedulingService.deleteAppointment(appointmentId));
    }

    @Override
    public ResponseEntity<AppointmentDetails> getAppointment(@NotNull @Valid BigDecimal appointmentId) {
        return ResponseEntity.of(appointmentSchedulingService.getAppointmentById(appointmentId));
    }

    @Override
    public ResponseEntity<UpdateAppointmentResponse> updateAppointment(@NotNull @Valid BigDecimal appointmentId, @Valid UpdateAppointmentRequest updateAppointmentRequest) {
        return ResponseEntity.ok(appointmentSchedulingService.updateAppointment(updateAppointmentRequest, appointmentId));
    }
}
