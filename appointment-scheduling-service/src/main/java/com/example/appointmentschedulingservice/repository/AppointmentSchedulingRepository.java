package com.example.appointmentschedulingservice.repository;

import com.api.project2.model.generated.appointmentscheduling.*;
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
public class AppointmentSchedulingRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AppointmentDetails getAppointmentById(BigDecimal appointmentId) {
        String sql = "SELECT * FROM appointments WHERE id = :appointmentId";

        var parameterSource = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId);

        var response = namedParameterJdbcTemplate.query(
                sql,
                parameterSource,
                new BeanPropertyRowMapper<>(AppointmentDetails.class)
        );

        return response.isEmpty() ? null : response.get(0);
    }

    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        String sql = "INSERT INTO appointments (idPatient, idDoctor, timestamp, type) " +
                "VALUES (:idPatient, :idDoctor, :timestamp, :type)";

        var parameterSource = new MapSqlParameterSource()
                .addValue("idPatient", createAppointmentRequest.getIdPatient())
                .addValue("idDoctor", createAppointmentRequest.getIdDoctor())
                .addValue("timestamp", createAppointmentRequest.getTimestamp())
                .addValue("type", createAppointmentRequest.getType().name());

        namedParameterJdbcTemplate.update(sql, parameterSource);

        var response = new CreateAppointmentResponse();
        response.setAppointmentId(BigDecimal.valueOf(parameterSource.getValue("id").hashCode())); // Mocked ID for simplicity
        return response;
    }

    public UpdateAppointmentResponse updateAppointment(UpdateAppointmentRequest updateAppointmentRequest, BigDecimal appointmentId) {
        String sql = "UPDATE appointments SET idPatient = :idPatient, idDoctor = :idDoctor, timestamp = :timestamp, type = :type " +
                "WHERE id = :appointmentId";

        var parameterSource = new MapSqlParameterSource()
                .addValue("idPatient", updateAppointmentRequest.getIdPatient())
                .addValue("idDoctor", updateAppointmentRequest.getIdDoctor())
                .addValue("timestamp", updateAppointmentRequest.getTimestamp())
                .addValue("type", updateAppointmentRequest.getType().name())
                .addValue("appointmentId", appointmentId);

        namedParameterJdbcTemplate.update(sql, parameterSource);

        var response = new UpdateAppointmentResponse();
        response.setResult("success");
        return response;
    }

    public DeleteAppointmentResponse deleteAppointment(BigDecimal appointmentId) {
        String sql = "DELETE FROM appointments WHERE id = :appointmentId";

        var parameterSource = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId);

        namedParameterJdbcTemplate.update(sql, parameterSource);

        var response = new DeleteAppointmentResponse();
        response.setResult("success");
        return response;
    }

}
