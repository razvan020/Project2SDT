package com.example.project2.controller;

import com.api.project2.controller.generated.patientsrecords.ApiApi;
import com.api.project2.model.generated.patientsrecords.*;
import com.example.project2.service.PatientRecordsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class PatientRecordsController implements ApiApi {


    private final PatientRecordsService patientRecordsService;

    @Override
    public ResponseEntity<PatientRecord> getPatientRecords(@NotNull @Valid BigDecimal patientId) {
        return ResponseEntity.ok(patientRecordsService.getPatientRecords(patientId));
    }

    @Override
    public ResponseEntity<CreatePatientRecordsResponse> createPatientRecords(@Valid CreatePatientRecordsRequest createPatientRecordsRequest) {
        return ResponseEntity.ok(patientRecordsService.createPatientRecords(createPatientRecordsRequest));
    }

    @Override
    public ResponseEntity<UpdatePatientRecordsResponse> updatePatientRecords(@NotNull @Valid BigDecimal patientId, @Valid UpdatePatientRecordsRequest updatePatientRecordsRequest) {
        return ResponseEntity.ok(patientRecordsService.updatePatientRecords(patientId, updatePatientRecordsRequest));
    }
}
