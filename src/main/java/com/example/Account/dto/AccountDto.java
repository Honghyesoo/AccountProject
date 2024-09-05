package com.example.Account.dto;

import com.example.Account.domain.Account;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long userId;
    private String accountNumber;// 계좌 번호
    private Long balance; // 계좌 잔액

    private LocalDateTime registeredAt; // 계좌 등록 일시
    private LocalDateTime unRegisteredAt; // 계좌 해지 일시

    public static  AccountDto fromEntity(Account account){
        return AccountDto.builder()
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .registeredAt(account.getRegisteredAt())
                .unRegisteredAt(account.getRegisteredAt())
                .build();
    }
}
