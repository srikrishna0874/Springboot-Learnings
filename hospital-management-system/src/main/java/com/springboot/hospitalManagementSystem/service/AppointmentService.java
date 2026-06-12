package com.codingshuttle.hospitalManagementSystem.service;

import com.codingshuttle.hospitalManagementSystem.entity.Appointment;
import com.codingshuttle.hospitalManagementSystem.entity.Doctor;
import com.codingshuttle.hospitalManagementSystem.entity.Patient;
import com.codingshuttle.hospitalManagementSystem.repository.AppointmentRepository;
import com.codingshuttle.hospitalManagementSystem.repository.DoctorRepository;
import com.codingshuttle.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorRepository doctorRepository;

    private final AppointmentRepository appointmentRepository;

    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);


        return appointmentRepository.save(appointment);
    }

}
