package com.example.mobile_banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID>  extends JpaRepository<T, ID> {
    void refresh(T entity);
    void remove(T entity);
}
