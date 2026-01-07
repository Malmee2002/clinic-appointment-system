package com.example.clinicappointmentsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialization;

    private Double fee;

    private String availability; // e.g., "Mon-Fri 9AM-5PM"

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;
}
