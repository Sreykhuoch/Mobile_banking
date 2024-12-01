package com.example.mobile_banking.Repository;

import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
}
