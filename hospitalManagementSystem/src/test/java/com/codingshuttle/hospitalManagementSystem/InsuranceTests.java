package com.codingshuttle.hospitalManagementSystem;

import com.codingshuttle.hospitalManagementSystem.entity.Appointment;
import com.codingshuttle.hospitalManagementSystem.entity.Insurance;
import com.codingshuttle.hospitalManagementSystem.repository.AppointmentRepository;
import com.codingshuttle.hospitalManagementSystem.repository.InsuranceRepository;
import com.codingshuttle.hospitalManagementSystem.service.AppointmentService;
import com.codingshuttle.hospitalManagementSystem.service.InsuranceService;
import com.codingshuttle.hospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Test
    public void testAssignInsuranceToPatient() {
        Insurance insurance = Insurance.builder()
                .provider("TATA Ergo")
                .policyNumber("TATA 232")
                .validUntil(LocalDate.of(2040, 1, 1))
                .build();

        Insurance updatedInsurance = insuranceService.assignInsuranceToPatient(insurance, 3L);

        System.out.println(updatedInsurance);

        patientService.deletePatient(1L);

    }

    @Test
    public void testCreateNewAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2024, 6, 30, 10, 0))
                .reason("OP")
                .build();

        Appointment savedAPpointment = appointmentService.createNewAppointment(appointment, 4L, 1L);

        System.out.println(savedAPpointment);

        patientService.deletePatient(4L);
    }
}
