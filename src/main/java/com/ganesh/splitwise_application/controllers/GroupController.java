package com.ganesh.splitwise_application.controllers;

import com.ganesh.splitwise_application.DTO.CreateGroupDto;
import com.ganesh.splitwise_application.DTO.GetGroupDto;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.DTO.UsersOnlyDto;
import com.ganesh.splitwise_application.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/group")
public class GroupController {
    GroupService groupService;

//    @Autowired
//    public GroupController(GroupService g){
//        service=g;
//    }
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupDto g){
//        List<Group> x=groupService.findAllByNameIs(g.getName());
//        if(x.size()==0) {
//            Group gr=groupService.creatGroup(g);
//            GetGroupDto ggd=GetGroupDto.builder().name(g.getName()).about(g.getAbout()).build();
//            //return new ResponseEntity<>("Group created with name "+g.getName(), HttpStatus.CREATED);
//            return new ResponseEntity<>(gr,HttpStatus.CREATED);
//        }
        Group gr=groupService.createGroup(g);
        return new ResponseEntity<>(gr,HttpStatus.CREATED);
    }

   // @RequestMapping("/{id}")
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<GetGroupDto> getGroup(@PathVariable("id") Long id){
        Group g=groupService.getGroup(id);
//        if(g==null)
//            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
       GetGroupDto ggd=GetGroupDto.builder().name(g.getName()).about(g.getAbout()).build();
        return new ResponseEntity<>(ggd,HttpStatus.FOUND);
    }
    @PatchMapping(value = "/{groupId}")
    public ResponseEntity<Group> addUsers(@PathVariable long groupId,@RequestBody UsersOnlyDto uod){
        Group g=groupService.addUsers(groupId,uod);
        return new ResponseEntity<>(g,HttpStatus.CREATED);
    }
}
