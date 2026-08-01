package com.springboot.security_app.SecurityApplication.dto;

import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private Long id;

    private String title;

    private String description;

    @ManyToOne
    private UserDto author;
}
