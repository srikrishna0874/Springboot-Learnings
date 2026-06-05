package com.codingshuttle.hospitalManagementSystem.dto;

import com.codingshuttle.hospitalManagementSystem.entity.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {
    private final BloodGroupType bloodGroupType;

    private final Long count;
}
