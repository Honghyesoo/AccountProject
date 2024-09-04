package com.example.Account.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // Redis 서버 주소와 포트를 설정합니다.
        config.useSingleServer().setAddress("redis://localhost:6379");
        // 필요에 따라 추가적인 설정을 여기에 추가할 수 있습니다.
        return Redisson.create(config);
    }
}
