package com.ganesh.splitwise_application.models;

import com.ganesh.splitwise_application.enums.TransactionType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="transactions")
public class Transaction extends BaseModel{

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn
    private User createdBy;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name="expense_id",referencedColumnName = "id")
    private Expense expense;

}
