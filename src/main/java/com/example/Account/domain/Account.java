package com.example.Account.domain;

import com.example.Account.exception.AccountException;
import com.example.Account.type.AccountStatus;
import com.example.Account.type.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account extends BaseEntity{
    @ManyToOne
    private AccountUser accountUser; // 소유자 정보
    private String accountNumber; // 계좌 번호

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus; // 계좌 상태
    private Long balance; // 계좌 잔액

    private LocalDateTime registeredAt; // 계좌 등록 일시
    private LocalDateTime unRegisteredAt; // 계좌 해지 일시

    public void useBalance(Long amount){
        if (amount > balance){
        throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
        balance -= amount;
    }

    public void CancelBalance(Long amount){
        if (amount < 0){
            throw new AccountException(ErrorCode.INVALID_REQUEST);
        }
        balance += amount;
    }

}
