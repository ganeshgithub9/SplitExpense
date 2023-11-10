package com.ganesh.splitwise_application.services;

import com.ganesh.splitwise_application.DTO.CreateUserDTO;
//import com.ganesh.splitwise_application.interfaces.BCryptEncoder;
import com.ganesh.splitwise_application.DTO.LoginDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.interfaces.PasswordProjection;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SelfUserService implements UserService{
    UserRepository userRepository;
    //GroupService groupService;
    //Encoder encoder;
    Converters converters;
    PasswordEncoder encoder;
    @Autowired
    public SelfUserService(UserRepository ur, @Qualifier("bcrypt") PasswordEncoder encoder,Converters converters){ // @Lazy annotation to terminate the dependency cycle
        userRepository =ur;
        this.encoder=encoder;
        this.converters=converters;
    }

    public User createUser(CreateUserDTO dto) throws SQLException {
        //User u=dto.user();
        //System.out.println("User service");
        User u=converters.createUserDtoToUser(dto);
        //PasswordEncoder passwordEncoder=encoder.getEncoder();
        String encoded=encoder.encode(u.getPassword());
        //System.out.println("encoded");
        u.setPassword(encoded);
//        System.out.println("User service before repository call");
        u=userRepository.save(u);
        //System.out.println("User service after repository call");
        //System.out.println(u.getPassword());
        return u;
    }

    public User getUser(Long id) {
        User u=userRepository.findById(id).orElse(null);
        //return userRepository.findById(id).;
        //u.setGroups(groupService.getGroups(id)); // to ignore lazy initialization exception for User.groups collection, created a query
        return u;
    }

//    @Transactional
//    public List<User> getUsers(List<Long> l){
//        return findAllById(l);
//    }
    public List<User> findAllByIdIn(List<Long> l){
        return userRepository.findAllByIdIn(l);
    }

    public boolean login(LoginDto ld) {
        PasswordProjection u=userRepository.findPasswordByMail(ld.getMail());
        //PasswordEncoder passwordEncoder=encoder.getEncoder();
        boolean check=encoder.matches(ld.getPassword(),u.getPassword());
        return check;
    }

    public User getUserByMail(String mail) {
        User u=userRepository.findByMail(mail);
        return u;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getGroupsAlongWithUser(Long id) {
        //return userRepository.getGroupsAlongWithUser();
        return null;
    }

    public boolean hello() {
        //return passwordEncoder.matches("abi","$2a$10$ZWuvfsfc/ONdYR.iH/1lo.XqYuKhGqK5x6Pksjry153RzDRMIi28y");
        return true;
    }
}



