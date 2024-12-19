package com.example.mobile_banking.api.account;


import com.example.mobile_banking.api.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user_account")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer  id;
    private Boolean isDisable;
    @CreationTimestamp
    private LocalDateTime  createdAt;
    @UpdateTimestamp
    private LocalDateTime  updatedAt;

    @ManyToOne(fetch =  FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
}

