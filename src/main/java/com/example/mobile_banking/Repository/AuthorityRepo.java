package com.example.mobile_banking.Repository;

import com.example.mobile_banking.api.authority.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepo extends BaseJpaRepository<Authority, Integer> {
}
