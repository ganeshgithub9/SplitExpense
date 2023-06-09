package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateUserDTO;
import com.ganesh.splitwise_application.DTO.GetUserDTO;
import com.ganesh.splitwise_application.DTO.LoginDto;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
//@RequestMapping("/user")
public class UserController {
    UserService userService;
//    @Autowired
//    public UserController(UserServiceImpl us){
//        userService =us;
//    }
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO dto){
        User u=userService.getUserByMail(dto.getMail());
        if(u==null) {
            userService.createUser(dto);
            return new ResponseEntity<>("Registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User already exists on this mail",HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<GetUserDTO> getUser(@PathVariable("id") Long id){
        User u= userService.getUser(id);
        if(u==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(GetUserDTO.builder().name(u.getName()).phone(u.getPhone()).mail(u.getMail()).build(),HttpStatus.FOUND);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto ld){
        boolean check=userService.login(ld);
        if(check)
            return new ResponseEntity<>("Logged in successfully",HttpStatus.OK);
        return new ResponseEntity<>("Log in failed",HttpStatus.UNAUTHORIZED);
    }
}
