package com.example.mobile_banking.api.authority;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    @JsonBackReference
    private List<Role> roles;
}

