package com.example.mobile_banking.api.account;


import com.example.mobile_banking.api.accountType.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "accounts")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

///one account can have many account types
//one account can have only one user, but user can have many accounts
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String actNo;
    private String actName;
    private Double transferLimit;
    private String pin;

    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<UserAccount> userAccounts;
}
