package com.example.project2.service;

import com.api.project2.model.generated.patientsrecords.*;
import com.example.project2.repository.PatientRecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PatientRecordsServiceImpl implements PatientRecordsService {

    private final PatientRecordsRepository patientRecordsRepository;

    @Override
    public CreatePatientRecordsResponse createPatientRecords(CreatePatientRecordsRequest request) {
        return patientRecordsRepository.createPatientRecord(request);
    }

    @Override
    public PatientRecord getPatientRecords(BigDecimal patientId) {
        return patientRecordsRepository.getPatientRecordByPatientId(patientId.toString());

    }

    @Override
    public UpdatePatientRecordsResponse updatePatientRecords(BigDecimal patientId, UpdatePatientRecordsRequest updatePatientRecordsRequest) {
        return patientRecordsRepository.updatePatientRecords(updatePatientRecordsRequest, patientId);
    }
}
