package com.example.Account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTestService {
    private final RedissonClient redissonClient;

    public String getLock() {
        RLock lock = redissonClient.getLock("sampleLock");

        try {
            // 락을 획득 시도, 대기 시간 1초, 락 유지 시간 5초
            boolean isLock = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (!isLock) {
                log.error("========Lock acquisition failed======");
                return "Lock failed";
            }
            // 락을 획득한 후, 추가 작업을 수행할 수 있습니다.
            // 예: 작업이 없을 경우, 아래 줄을 주석 처리
            log.info("Lock acquired, performing work...");

            // 5초 후에 락이 자동으로 해제되므로, 별도의 unlock 호출이 필요 없습니다.

        } catch (Exception e) {
            log.error("Redis lock failed", e);
            return "Lock failed due to exception";
        }
        return "Lock success";
    }
}
