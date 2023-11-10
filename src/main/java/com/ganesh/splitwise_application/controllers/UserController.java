package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateUserDTO;
import com.ganesh.splitwise_application.DTO.GetUserDTO;
import com.ganesh.splitwise_application.DTO.LoginDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.models.User;
import com.ganesh.splitwise_application.services.SelfUserService;
import com.ganesh.splitwise_application.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    Converters converters;
    public UserController(UserService us,Converters converters){
        userService =us;
        this.converters=converters;
    }
    @PostMapping
    public ResponseEntity<GetUserDTO> createUser(@Valid  @RequestBody  CreateUserDTO dto) throws SQLException {
//        User u= userService.getUserByMail(dto.getMail());
//        if(u==null) {
//            userService.createUser(dto);
//            return new ResponseEntity<>("Registered successfully", HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>("User already exists on this mail",HttpStatus.CONFLICT); //user exists
        //System.out.println("User controller");
        User  user=userService.createUser(dto);
        GetUserDTO getUserDTO=converters.userToGetUserDTO(user);
        return new ResponseEntity<>(getUserDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GetUserDTO> getUser(@PathVariable("id") Long id) throws NullPointerException{
        User u= userService.getUser(id);
        if(u==null)
            throw new NullPointerException();
            //return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); //no user found
        //return new ResponseEntity<>(GetUserDTO.builder().name(u.getName()).phone(u.getPhone()).mail(u.getMail()).build(),HttpStatus.FOUND);

        return new ResponseEntity<>(converters.userToGetUserDTO(u),HttpStatus.FOUND);
    }

     @GetMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto ld){
        boolean check= userService.login(ld);
        if(check)
            return new ResponseEntity<>("Logged in successfully",HttpStatus.OK);
        return new ResponseEntity<>("Log in failed",HttpStatus.UNAUTHORIZED); //Invalid mail or password
    }

    //security trial
//    @GetMapping("/registerUser")
//    public String registerUser(Model m){
//        m.addAttribute("user",new CreateUserDTO());
//        return "register_user";
//    }
    /*public ModelAndView hello(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("hello.html");
        return mv;
    }*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }
}
