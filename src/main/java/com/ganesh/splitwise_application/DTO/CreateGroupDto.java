package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CreateGroupDto {
    private String name,about;
    private List<Long> users;
}
