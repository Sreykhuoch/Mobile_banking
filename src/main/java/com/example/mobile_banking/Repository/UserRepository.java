package com.example.mobile_banking.Repository;

import com.example.mobile_banking.api.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends BaseJpaRepository<User, Integer> {

  Optional<User> findByEmailAndDeletedIsFalse(String email);

  Optional<User> findByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);

  @Modifying
  @Transactional   //ensure that the modification query is executed within a transaction
  @Query("UPDATE User SET isDeleted =  ?1 WHERE uuid = ?2")
  void updateIsDeletedByUuid(Boolean isDeleted, String uuid);
}
