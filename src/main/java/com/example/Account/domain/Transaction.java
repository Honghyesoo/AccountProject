package com.example.Account.domain;

import com.example.Account.type.AccountStatus;
import com.example.Account.type.TransactionResultType;
import com.example.Account.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; //거래의종류(사용,사용취소)
    @Enumerated(EnumType.STRING)
    private TransactionResultType transactionResultType; //거래결과(성공,실패)

    @ManyToOne
    private Account account; // 거래가 발생계좌(N:1 연결)
    private Long amount; // 거래금액
    private Long balanceSnapshot; // 거래 후 계좌 잔액

    private String transactionId; // 계좌 해지 일시
    private LocalDateTime transactedAt; // 거래 일시

}
