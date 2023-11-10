package com.ganesh.splitwise_application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginDto {
    @NotBlank(message = "Please provide mail ID")
    String mail;
    @NotBlank(message = "Please enter Password")
            @Size(min = 8)
    String password;
}
