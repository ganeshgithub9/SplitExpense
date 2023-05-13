package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateUserDTO;
//import com.ganesh.splitwise_application.interfaces.BCryptEncoder;
import com.ganesh.splitwise_application.DTO.LoginDto;
import com.ganesh.splitwise_application.interfaces.PasswordProjection;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    GroupService groupService;
    PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository ur, @Lazy GroupService gs,PasswordEncoder pe){ // @Lazy annotation to terminate the dependency cycle
        userRepository =ur;groupService=gs;
        passwordEncoder=pe;
    }

    public User createUser(CreateUserDTO dto){
        User u=dto.user();
        //pe=new BCryptEncoder();
        String encoded=passwordEncoder.encode(u.getPassword());
        u.setPassword(encoded);
        return userRepository.save(u);
    }
    @Transactional
    public User getUser(Long id) {
        User u=userRepository.findById(id).orElse(null);
        //return userRepository.findById(id).;
        //u.setGroups(groupService.getGroups(id)); // to ignore lazy initialization exception for User.groups collection, created a query
        return u;
    }

    @Transactional
    public List<User> getUsers(List<Long> l){
        return findAllById(l);
    }
    public List<User> findAllById(List<Long> l){
        return userRepository.findAllById(l);
    }

    public boolean login(LoginDto ld) {
        PasswordProjection u=userRepository.findPasswordByMail(ld.getMail());
        boolean check=passwordEncoder.matches(ld.getPassword(),u.getPassword());
        return check;
    }

    public User getUserByMail(String mail) {
        User u=userRepository.findByMail(mail);
        return u;
    }
}



