package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Setter
@Getter
public class CreateUserDTO {
    @NotBlank(message = "User name is mandatory")
    String name;
    @NotBlank(message = "Please provide the mail")
    String mail;
    @NotBlank(message = "Please enter the mobile")
    @Size(min = 10,message = "Not a valid phone number")
    String phone;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8)
    String password;

//    public User user() {
//        User u=new User();
//        u.setName(this.getName());
//        u.setMail(this.getMail());
//        u.setPhone(this.getPhone());
//        u.setPassword(this.getPassword());
//        return u;
//    }
}
