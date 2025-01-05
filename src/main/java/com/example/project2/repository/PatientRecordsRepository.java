package com.example.project2.repository;

import com.api.project2.model.generated.patientsrecords.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRecordsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PatientRecord getPatientRecordByPatientId(String patientId) {
        String sql = "SELECT * FROM patient_records WHERE patientId = :patientId ";

        var parameterSource = new MapSqlParameterSource()
                .addValue("patientId", patientId);

        var response = namedParameterJdbcTemplate.query(
                sql,
                parameterSource,
                new BeanPropertyRowMapper<>(PatientRecord.class)
                );

        return response.getFirst();
    }

    public CreatePatientRecordsResponse createPatientRecord(CreatePatientRecordsRequest patientRecord) {
        String sql = "INSERT INTO patient_records (patientId, patientName) " +
                "VALUES (:patientId, :patientName)";

        var parameterSource = new MapSqlParameterSource()
                .addValue("patientId", patientRecord.getPatientId())
                .addValue("patientName", patientRecord.getPatientName());

        namedParameterJdbcTemplate.update(sql, parameterSource);

        var response = new CreatePatientRecordsResponse();
        response.setResult("success");
        return response;

    }

    public UpdatePatientRecordsResponse updatePatientRecords(UpdatePatientRecordsRequest updatePatientRecord, BigDecimal patientId) {
        String sql = "UPDATE patient_records " +
                "SET patientName = :patientName, weight = :weight " +
                "WHERE patientId = :patientId";

        var parameterSource = new MapSqlParameterSource()
                .addValue("patientName", updatePatientRecord.getPatientName())
                .addValue("weight", updatePatientRecord.getWeight())
                .addValue("patientId", patientId);

        namedParameterJdbcTemplate.update(sql, parameterSource);

        var response = new UpdatePatientRecordsResponse();
        response.setResult("success");
        return response;

    }

}
