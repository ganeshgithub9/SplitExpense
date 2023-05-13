package com.ganesh.splitwise_application.models;

import com.ganesh.splitwise_application.enums.TransactionType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name="transactions")
public class Transaction extends BaseModel{

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User payer;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name="exp_id",referencedColumnName = "id")
    private Expense expense;
}
