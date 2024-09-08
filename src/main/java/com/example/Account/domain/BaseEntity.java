package com.example.Account.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "doesNotUseThisBuilder")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id //프라이머리 키
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt; // 생성 일시
    @LastModifiedDate
    private LocalDateTime updatedAt; // 최종 수정 일시
}
