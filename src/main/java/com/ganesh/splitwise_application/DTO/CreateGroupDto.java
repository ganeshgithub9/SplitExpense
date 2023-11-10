package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CreateGroupDto {
    @NotBlank(message = "Please give group a name")
    private String name;
    private String about;
    private List<Long> users;
    private Long createdById;
}
