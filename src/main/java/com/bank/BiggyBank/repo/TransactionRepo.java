package com.bank.BiggyBank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.BiggyBank.model.Transaction;


public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByAccountId(int id);

}
