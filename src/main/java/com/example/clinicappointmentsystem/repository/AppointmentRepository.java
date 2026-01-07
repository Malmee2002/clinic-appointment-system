package com.example.clinicappointmentsystem.repository;

import com.example.clinicappointmentsystem.entity.Appointment;
import com.example.clinicappointmentsystem.entity.Doctor;
import com.example.clinicappointmentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient(User patient);
    List<Appointment> findByDoctor(Doctor doctor);

    @Query(value = "SELECT * FROM appointments WHERE doctor_id = :doctorId AND appointment_date = :date AND appointment_time = CAST(:time AS TIME)", nativeQuery = true)
    Optional<Appointment> findByDoctorAndAppointmentDateAndAppointmentTime(
            @Param("doctorId") Long doctorId,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time
    );
}