package com.example.Account.repository;

import com.example.Account.domain.Account;
import com.example.Account.domain.AccountUser;
import com.example.Account.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//Account 페이지를 접속하기 위한 인터페이스
@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction,Long> {

}
