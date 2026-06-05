package com.codingshuttle.hospitalManagementSystem.repository;

import com.codingshuttle.hospitalManagementSystem.dto.BloodGroupStats;
import com.codingshuttle.hospitalManagementSystem.dto.CPatientInfo;
import com.codingshuttle.hospitalManagementSystem.dto.IPatientInfo;
import com.codingshuttle.hospitalManagementSystem.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByEmailContaining(String emailText);

    @Query("select p.id as id, p.name as name, p.email as email from Patient p")
    List<IPatientInfo> getAllPatientsInfo();

    @Query("select new com.codingshuttle.hospitalManagementSystem.dto.CPatientInfo(p.id, p.name) " +
            "from Patient p")
    List<CPatientInfo> getALlPatientsInfoConcrete();

    @Query("select new com.codingshuttle.hospitalManagementSystem.dto.BloodGroupStats(p.bloodGroupType, " +
            "COUNT(p)) from Patient p group by p.bloodGroupType order by COUNT(p) DESC")
    List<BloodGroupStats> getBloodGroupStats();

    @Transactional
    @Modifying
    @Query("update Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);
}
