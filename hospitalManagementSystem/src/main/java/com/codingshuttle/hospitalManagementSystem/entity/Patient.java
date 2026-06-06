package com.codingshuttle.hospitalManagementSystem.entity;

import com.codingshuttle.hospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    @Enumerated(value = EnumType.STRING)
    private BloodGroupType bloodGroupType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL) // Owning
    @JoinColumn(name = "patient_insurance", unique = true)
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL) //inverse
    private Set<Appointment> appointments = new HashSet<>();
}
