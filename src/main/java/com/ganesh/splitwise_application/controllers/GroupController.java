package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateGroupDto;
import com.ganesh.splitwise_application.DTO.GetGroupDto;
import com.ganesh.splitwise_application.converters.Converters;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.DTO.UsersOnlyDto;
import com.ganesh.splitwise_application.services.GroupService;
import com.ganesh.splitwise_application.services.SelfGroupService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {
    GroupService groupService;
    Converters converters;

//    @Autowired
    public GroupController(GroupService g,Converters converters){
        groupService=g;
        this.converters=converters;
    }
    @PostMapping
    public ResponseEntity<GetGroupDto> createGroup(@Valid @RequestBody CreateGroupDto g){
//        List<Group> x=groupService.findAllByNameIs(g.getName());
//        if(x.size()==0) {
//            Group gr=groupService.creatGroup(g);
//            GetGroupDto ggd=GetGroupDto.builder().name(g.getName()).about(g.getAbout()).build();
//            //return new ResponseEntity<>("Group created with name "+g.getName(), HttpStatus.CREATED);
//            return new ResponseEntity<>(gr,HttpStatus.CREATED);
//        }
        System.out.println("group controller");
        GetGroupDto gr=groupService.createGroup(g);
        //GetGroupDto dto= converters.groupToGetGroupDTO(gr);
        return new ResponseEntity<>(gr,HttpStatus.CREATED);
    }

   // @RequestMapping("/{id}")
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<GetGroupDto> getGroup(@PathVariable("id") Long id){
        GetGroupDto g=groupService.getGroup(id);
        if(g==null)
            throw  new NullPointerException(); //if group id not found
       //GetGroupDto ggd= converters.groupToGetGroupDTO(g);
        return new ResponseEntity<>(g,HttpStatus.FOUND);
    }
    @PatchMapping(value = "/{groupId}")
    public ResponseEntity<GetGroupDto> addUsers(@PathVariable long groupId,@RequestBody UsersOnlyDto uod) throws NullPointerException{
        GetGroupDto g=groupService.addUsers(groupId,uod);
        return new ResponseEntity<>(g,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String > deleteGroup(@PathVariable Long id){
        String name=groupService.deleteGroup(id);
        if(name==null)
            throw new NullPointerException();
        return new ResponseEntity<>(name,HttpStatus.OK);
    }

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
