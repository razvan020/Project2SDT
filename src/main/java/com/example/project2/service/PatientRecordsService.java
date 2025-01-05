package com.example.project2.service;

import com.api.project2.model.generated.patientsrecords.*;

import java.math.BigDecimal;

public interface PatientRecordsService {

    CreatePatientRecordsResponse createPatientRecords(CreatePatientRecordsRequest request);

    PatientRecord getPatientRecords(BigDecimal patientId);

    UpdatePatientRecordsResponse updatePatientRecords(BigDecimal patientId, UpdatePatientRecordsRequest updatePatientRecordsRequest);
}
