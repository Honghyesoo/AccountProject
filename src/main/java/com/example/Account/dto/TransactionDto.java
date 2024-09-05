package com.example.Account.dto;

import com.example.Account.domain.Account;
import com.example.Account.domain.Transaction;
import com.example.Account.type.TransactionResultType;
import com.example.Account.type.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private String accountNumber; // 계좌 번호
    private TransactionType transactionType; //거래의종류(사용,사용취소)
    private TransactionResultType transactionResultType; //거래결과(성공,실패)
    private Long amount; // 거래금액
    private Long balanceSnapshot; // 거래 후 계좌 잔액
    private String transactionId; // 계좌 해지 일시
    private LocalDateTime transactedAt; // 거래 일시

    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .accountNumber(transaction.getAccount().getAccountNumber())
                .transactionType(transaction.getTransactionType())
                .transactionResultType(transaction.getTransactionResultType())
                .amount(transaction.getAmount())
                .balanceSnapshot(transaction.getBalanceSnapshot())
                .transactionId(transaction.getTransactionId())
                .transactedAt(transaction.getTransactedAt())
                .build();
    }

}
