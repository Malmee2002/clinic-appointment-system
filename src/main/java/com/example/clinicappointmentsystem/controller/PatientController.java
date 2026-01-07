package com.example.clinicappointmentsystem.controller;

import com.example.clinicappointmentsystem.entity.Appointment;
import com.example.clinicappointmentsystem.entity.Doctor;
import com.example.clinicappointmentsystem.entity.User;
import com.example.clinicappointmentsystem.service.AppointmentService;
import com.example.clinicappointmentsystem.service.DoctorService;
import com.example.clinicappointmentsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        User patient = userService.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Load patient's appointments
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patient);

        model.addAttribute("patient", patient);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("appointments", appointments);
        return "patient_dashboard";
    }

    @GetMapping("/book/{id}")
    public String bookForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("appointment", new Appointment());
        return "appointment_form";
    }

    @PostMapping("/book")
    public String bookAppointment(@RequestParam("doctorId") Long doctorId,
                                  @ModelAttribute Appointment appointment,
                                  Authentication auth,
                                  Model model) {

        User patient = userService.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the managed Doctor entity
        Doctor doctor = doctorService.getDoctorById(doctorId);

        // Check slot availability
        if (!appointmentService.isSlotAvailable(
                doctor,
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime())) {

            model.addAttribute("error", "This slot is not available");
            model.addAttribute("doctor", doctor);
            return "appointment_form";
        }

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus("PENDING");

        Appointment savedAppointment = appointmentService.saveAppointment(appointment);

        model.addAttribute("appointment", savedAppointment);
        return "appointment_confirmation";
    }

    @GetMapping("/appointments")
    public String myAppointments(Authentication auth, Model model) {
        User patient = userService.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("appointments",
                appointmentService.getAppointmentsByPatient(patient));

        return "patient_appointments";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        Appointment appt = appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appt.setStatus("CANCELLED");
        appointmentService.saveAppointment(appt);

        return "redirect:/patient/dashboard";
    }
}