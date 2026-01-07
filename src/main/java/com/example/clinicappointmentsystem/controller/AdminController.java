package com.example.clinicappointmentsystem.controller;

import com.example.clinicappointmentsystem.entity.Appointment;
import com.example.clinicappointmentsystem.entity.Doctor;
import com.example.clinicappointmentsystem.entity.User;
import com.example.clinicappointmentsystem.service.AppointmentService;
import com.example.clinicappointmentsystem.service.DoctorService;
import com.example.clinicappointmentsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<User> patients = userService.findAllPatients();

        model.addAttribute("doctors", doctors);
        model.addAttribute("appointments", appointments);
        model.addAttribute("patients", patients);
        return "admin_dashboard";
    }

    // ===== DOCTOR MANAGEMENT =====

    // Show form to add new doctor
    @GetMapping("/doctor/add")
    public String addDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor_form";
    }

    // Save new doctor
    @PostMapping("/doctor/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/admin/dashboard";
    }

    // Show form to edit existing doctor
    @GetMapping("/doctor/edit/{id}")
    public String editDoctorForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("doctor", doctor);
        return "doctor_form";
    }

    // Save edited doctor
    @PostMapping("/doctor/edit/{id}")
    public String editDoctor(@PathVariable Long id, @ModelAttribute Doctor doctor) {
        doctor.setId(id);
        doctorService.saveDoctor(doctor);
        return "redirect:/admin/dashboard";
    }

    // Delete doctor
    @GetMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/admin/dashboard";
    }

    // ===== APPOINTMENT MANAGEMENT =====

    // Update appointment status
    @PostMapping("/appointment/status")
    public String updateAppointmentStatus(@RequestParam Long id, @RequestParam String status) {
        Appointment appt = appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appt.setStatus(status);
        appointmentService.saveAppointment(appt);
        return "redirect:/admin/dashboard";
    }
}