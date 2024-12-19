package com.example.mobile_banking.api.accountType;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "accountTypes")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
