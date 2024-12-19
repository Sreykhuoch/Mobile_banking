package com.example.mobile_banking.Repository;

import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends BaseJpaRepository<UserRole, Integer> {
   List<UserRole> findByUser(User user);

   void deleteByUser(User user);
}
