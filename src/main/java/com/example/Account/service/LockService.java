package com.example.Account.service;

import com.example.Account.exception.AccountException;
import com.example.Account.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LockService {
    private final RedissonClient redissonClient;

    public void lock(String accountNumber) {
        RLock lock = redissonClient.getLock(getLockKey(accountNumber));
        log.info("Trying lock for accountNumber : {}" , accountNumber);

        try {
            // 락을 획득 시도, 대기 시간 1초, 락 유지 시간 5초
            boolean isLock = lock.tryLock(1, 15, TimeUnit.SECONDS);
            if (!isLock) {
                log.info("========Lock acquisition failed======");
               throw new AccountException(ErrorCode. ACCOUNT_TRANSACTION_LOCK);
            }
        }catch (AccountException e){
            throw e;
        }
        catch (Exception e) {
            log.info("Redis lock failed", e);
        }
    }

    public void unlock(String accountNumber){
        log.info("Unlock for  accountNumber: {} ", accountNumber);
        redissonClient.getLock(getLockKey(accountNumber)).unlock();
    }

    private static String getLockKey(String accountNumber) {
        return "ACLK:" + accountNumber;
    }
}
