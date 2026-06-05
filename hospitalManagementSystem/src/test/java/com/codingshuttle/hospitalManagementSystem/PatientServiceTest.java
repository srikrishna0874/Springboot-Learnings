package com.codingshuttle.hospitalManagementSystem;

import com.codingshuttle.hospitalManagementSystem.dto.BloodGroupStats;
import com.codingshuttle.hospitalManagementSystem.dto.CPatientInfo;
import com.codingshuttle.hospitalManagementSystem.dto.IPatientInfo;
import com.codingshuttle.hospitalManagementSystem.entity.Patient;
import com.codingshuttle.hospitalManagementSystem.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatient() {
//        List<Patient> patients = patientRepository.findAll();

//        List<CPatientInfo> patients = patientRepository.getALlPatientsInfoConcrete();

//        List<BloodGroupStats> bloodGroupStats = patientRepository.getBloodGroupStats();
//
//        for(BloodGroupStats patient : bloodGroupStats){
//            System.out.println(patient);
//        }

        int rowsAffected = patientRepository.updatePatientNameWithId("Sri Krishna", 91L);

        System.out.println("rowsAffected = " + rowsAffected);
    }
}
