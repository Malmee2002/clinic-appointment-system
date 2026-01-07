package com.example.clinicappointmentsystem.service;

import com.example.clinicappointmentsystem.entity.Appointment;
import com.example.clinicappointmentsystem.entity.Doctor;
import com.example.clinicappointmentsystem.entity.User;
import com.example.clinicappointmentsystem.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public boolean isSlotAvailable(Doctor doctor,
                                   LocalDate date,
                                   LocalTime time) {
        Optional<Appointment> existing =
                appointmentRepository.findByDoctorAndAppointmentDateAndAppointmentTime(
                        doctor.getId(), date, time  // Changed: Pass doctor.getId() instead of doctor
                );
        return existing.isEmpty();
    }

    public List<Appointment> getAppointmentsByPatient(User patient) {
        return appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}