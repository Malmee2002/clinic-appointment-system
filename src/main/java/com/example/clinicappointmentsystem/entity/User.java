package com.example.clinicappointmentsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String password;

    private String role; // ADMIN or PATIENT

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}

