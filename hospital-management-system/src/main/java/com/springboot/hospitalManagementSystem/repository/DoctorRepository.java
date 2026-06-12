package com.codingshuttle.hospitalManagementSystem.repository;

import com.codingshuttle.hospitalManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}