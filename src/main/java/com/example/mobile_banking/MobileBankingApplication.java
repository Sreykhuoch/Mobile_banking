package com.example.mobile_banking;

import com.example.mobile_banking.Repository.BaseJpaRepository;
import com.example.mobile_banking.base.BaseJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class)
@SpringBootApplication
public class MobileBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileBankingApplication.class, args);
	}

}
