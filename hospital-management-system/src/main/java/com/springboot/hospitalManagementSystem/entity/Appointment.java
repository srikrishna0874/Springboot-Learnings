package com.codingshuttle.hospitalManagementSystem.entity;

import com.codingshuttle.hospitalManagementSystem.repository.PatientRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
    @ToString.Exclude
    @JsonIgnore
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY) //Owning
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Doctor doctor;

}
