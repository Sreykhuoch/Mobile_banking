package com.example.mobile_banking.base;

import com.example.mobile_banking.Repository.BaseJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseJpaRepositoryImpl(Class<T> domainClass, EntityManager entityManager, EntityManager entityManager1) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void refresh(T entity) {
        entityManager.refresh(entity);
        
    }

    @Override
    public void remove(T entity){
        entityManager.remove(entity);
    }
}
