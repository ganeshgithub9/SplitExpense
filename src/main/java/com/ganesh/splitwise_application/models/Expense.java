package com.ganesh.splitwise_application.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name = "expenses")
//@Table to change the table name
//@Column to change the column name
public class Expense extends BaseModel {
    private String name;
    private Double amount;

    @ManyToOne
    @JoinColumn(name="group_id",referencedColumnName = "id")
    private Group group;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User createdBy;

    @OneToMany(mappedBy = "expense",cascade = {CascadeType.ALL})
    private List<Transaction> transactions=new ArrayList<>();
//    @OneToMany(mappedBy = "expense")
//    private List<Transaction> billPerPerson=new ArrayList<>();
}
