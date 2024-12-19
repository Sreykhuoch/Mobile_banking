package com.example.mobile_banking.api.authority;

import com.example.mobile_banking.api.user.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<UserRole>  userRoles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;

    @Override
    public String getAuthority() {
        return "ROLE" + name;
    }
}
