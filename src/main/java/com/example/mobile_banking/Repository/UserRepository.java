package com.example.mobile_banking.Repository;

import com.example.mobile_banking.api.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
