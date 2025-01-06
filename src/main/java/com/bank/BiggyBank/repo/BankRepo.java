package com.bank.BiggyBank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.BiggyBank.model.BankUsers;

@Repository
public interface BankRepo extends JpaRepository<BankUsers, Integer> {

}
