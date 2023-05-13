package com.ganesh.splitwise_application.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ganesh.splitwise_application.DTO.CreateGroupDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "groups")
public class Group extends BaseModel{
    private String name,about;

    //@JsonManagedReference      //starting point of infinite recursion
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name="groups_users",joinColumns = {@JoinColumn(name="group_id",referencedColumnName ="id")},inverseJoinColumns = {@JoinColumn(name="user_id",referencedColumnName ="id")}) //hibernate creates the table automatically
    private List<User> users=new ArrayList<>();

//    @OneToMany(mappedBy = "group")
//    private List<Expense> expenses=new ArrayList<>();  //no need of bidirectional as it is onetomany and child table

//    public CreateGroupDto toDto() {
//        CreateGroupDto d=new CreateGroupDto();
//        d.setName(this.getName());
//        d.setAbout(this.getAbout());    // removed since it is also appearing when we print Group model
//        d.setUsers(this.getUsers());
//        return d;
//    }
}
