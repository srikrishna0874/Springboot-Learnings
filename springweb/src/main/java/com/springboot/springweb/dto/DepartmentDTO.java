package com.codingshuttle.springweb.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long Id;

    @NotBlank(message = "Title of the department shouldn't be blank.")
    private String title;

    @NotBlank
    @Size(min = 3, max = 20, message = "The length of department code should be in the range [3, 20]")
    private String departmentCode;

    @JsonProperty(value = "isActive")
    @AssertTrue(message = "Department should be active.")
    private Boolean isActive;

    @PastOrPresent(message = "The created_at time shouldn't be in future.")
    private LocalDateTime createdAt;
}
