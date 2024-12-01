package com.example.mobile_banking.api.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private String name;
    private String gender;
    private String phoneNumber;
    private  String email;
    private String password;
    private String oneSignalId;
    private String studentCardNo;
    private String verifiedCode;
    private boolean isDeleted;
    private boolean isStudent;
    private boolean isVerified;

    @OneToMany(mappedBy = "user")
    List<UserRole>  userRole;

}
