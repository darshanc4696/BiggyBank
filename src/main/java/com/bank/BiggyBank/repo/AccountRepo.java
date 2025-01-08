package com.bank.BiggyBank.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.BiggyBank.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
	
	Optional<Account> findByName(String name);

}
