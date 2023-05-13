package com.ganesh.splitwise_application.DTO;

import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetExpenseDto {
    String name;
    Double amount;
    GetGroupDto getGroupDto;
    GetUserDTO getUserDTO;
}
