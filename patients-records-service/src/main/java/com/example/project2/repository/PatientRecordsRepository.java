package com.example.project2.repository;

import com.api.project2.model.generated.appointmentscheduling.AppointmentDetails;
import com.api.project2.model.generated.patientsrecords.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
@RequiredArgsConstructor
public class PatientRecordsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;


    public PatientRecord getPatientRecordByPatientId(Integer patientId) {

        String sql = """
         SELECT pr.patientId,
                pr.diagnosis,
                pr.treatment,
                pr.notes,
                p.patient_name AS patientName
                FROM patient_records pr
                JOIN patients p ON pr.patientId = p.patient_id
                LEFT JOIN appointments a ON pr.patientId = a.id_patient
                WHERE p.patient_id = :patientId
    """;

        var parameterSource = new MapSqlParameterSource()
                .addValue("patientId", patientId);

        var response = namedParameterJdbcTemplate.query(
                sql,
                parameterSource,
                new BeanPropertyRowMapper<>(PatientRecord.class)
                );

        PatientRecord patientRecord = response.getFirst();

        String appointmentsServiceUrl = discoveryClient.getInstances("appointment-scheduling-service")
                .stream()
                .findFirst()
                .map(instance -> instance.getUri().toString())
                .orElseThrow(() -> new RuntimeException("Appointments service not available"));

        System.out.println("Resolved URL: " + appointmentsServiceUrl);

        String url = appointmentsServiceUrl + "/api/v1/appointments/getAppointment?appointmentId=" + patientId;

        ResponseEntity<AppointmentDetails> appointmentResponse = restTemplate.getForEntity(url, AppointmentDetails.class);

        if (appointmentResponse.getStatusCode() == HttpStatus.OK && appointmentResponse.getBody() != null) {
            patientRecord.setAppointmentDate(appointmentResponse.getBody().getTimestamp());
        }

        return patientRecord;
    }

    public CreatePatientRecordsResponse createPatientRecord(CreatePatientRecordsRequest patientRecord) {
        String sql = "INSERT INTO patient_records (diagnosis, treatment,notes) " +
                "VALUES (:diagnosis, :treatment, :notes)";

        var parameterSource = new MapSqlParameterSource()
                .addValue("diagnosis", patientRecord.getDiagnosis())
                .addValue("treatment", patientRecord.getTreatment())
                .addValue("notes", patientRecord.getNotes());

        namedParameterJdbcTemplate.update(sql, parameterSource);

        var response = new CreatePatientRecordsResponse();
        response.setResult("success");
        return response;

    }

    public UpdatePatientRecordsResponse updatePatientRecords(Integer patientId, UpdatePatientRecordsRequest updatePatientRecord) {
        String sql = "UPDATE patient_records " +
                "SET diagnosis = :diagnosis, treatment = :treatment , notes = :notes " +
                "WHERE patientId = :patientId";

        var parameterSource = new MapSqlParameterSource()
                .addValue("diagnosis", updatePatientRecord.getDiagnosis())
                .addValue("treatment", updatePatientRecord.getTreatment())
                .addValue("notes", updatePatientRecord.getNotes())
                .addValue("patientId", patientId);

        namedParameterJdbcTemplate.update(sql, parameterSource);

        var response = new UpdatePatientRecordsResponse();
        response.setResult("success");
        return response;

    }

}
