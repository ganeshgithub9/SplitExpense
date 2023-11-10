package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateGroupDto;
import com.ganesh.splitwise_application.DTO.GetGroupDto;
import com.ganesh.splitwise_application.DTO.UsersOnlyDto;
import com.ganesh.splitwise_application.models.Group;

public interface GroupService {
    GetGroupDto createGroup(CreateGroupDto g);
    GetGroupDto getGroup(Long id);
    GetGroupDto addUsers(Long groupId, UsersOnlyDto u) throws NullPointerException;

    String deleteGroup(Long id);

    Group findById(long groupId);
}
