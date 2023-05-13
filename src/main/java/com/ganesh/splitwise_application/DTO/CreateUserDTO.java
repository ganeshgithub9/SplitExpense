package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateUserDTO {
    String name,mail,phone,password;

    public User user() {
        User u=new User();
        u.setName(this.getName());
        u.setMail(this.getMail());
        u.setPhone(this.getPhone());
        u.setPassword(this.getPassword());
        return u;
    }
}
