package com.ganesh.splitwise_application.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ganesh.splitwise_application.DTO.GetUserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="users") //to optimize the login process, created index on mail
public class User extends BaseModel{
    private String name,phone,password;
    @Column(name = "mail",unique = true,nullable = false)
    private String mail;
//    @OneToMany(mappedBy = "payer")
//    private List<Transaction> transactions=new ArrayList<>();   //no need of bidirectional as it is onetomany and child table
////
////    @JsonBackReference // end point of infinite recursion. eager fetch for both bidirectional relationships lead to infinite recursion
    @ManyToMany(mappedBy = "users")  // lazy fetch will not fetch set of groups while printing user, written a query to overcome this
    private List<Group> groups=new ArrayList<>(); // list is not a good container when it comes to JPA,ex: if we want to add a group, list will delete existing groups and reinitialize them. It can be overcome by set according to some resources.

    @OneToMany(mappedBy = "createdBy")
    private List<Group> groupsCreated=new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    private List<Expense> expensesCreated=new ArrayList<>();  //no need of bidirectional as it is onetomany and child table

    @OneToMany(mappedBy = "user")
    private List<Transaction> userTransactions=new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    private List<Transaction> transactionsCreated=new ArrayList<>();
}
