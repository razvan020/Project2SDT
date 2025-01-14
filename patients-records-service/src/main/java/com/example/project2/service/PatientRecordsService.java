package com.example.project2.service;

import com.api.project2.model.generated.patientsrecords.*;

import java.math.BigDecimal;

public interface PatientRecordsService {

    CreatePatientRecordsResponse createPatientRecords(CreatePatientRecordsRequest request);

    PatientRecord getPatientRecords(Integer patientId);

    UpdatePatientRecordsResponse updatePatientRecords(Integer patientId, UpdatePatientRecordsRequest updatePatientRecordsRequest);
}
