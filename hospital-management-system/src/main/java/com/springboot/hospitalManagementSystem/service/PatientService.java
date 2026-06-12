package com.codingshuttle.hospitalManagementSystem.service;

import com.codingshuttle.hospitalManagementSystem.entity.Patient;
import com.codingshuttle.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;


    @Transactional
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patientRepository.delete(patient);
    }
}
