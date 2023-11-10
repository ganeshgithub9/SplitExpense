package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateUserDTO;
import com.ganesh.splitwise_application.DTO.LoginDto;
import com.ganesh.splitwise_application.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User createUser(CreateUserDTO dto) throws SQLException;
    User getUser(Long id);
    //List<User> getUsers(List<Long> l);
    List<User> findAllByIdIn(List<Long> l);
    boolean login(LoginDto ld);
    User getUserByMail(String mail);
    User findById(Long id);

    User getGroupsAlongWithUser(Long id);
}
