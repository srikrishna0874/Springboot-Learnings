package com.codingshuttle.hospitalManagementSystem.entity;

import com.codingshuttle.hospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne //Owning
    @JoinColumn(nullable = false)
    private Patient patient;

    @ManyToOne //Owning
    @JoinColumn(nullable = false)
    private Doctor doctor;

}
