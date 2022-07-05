package com.example.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/5 23:42
 * 令牌桶算法
 */
@Slf4j(topic = "TokenBucket")
public class TokenBucket {

    private AtomicInteger phoneNumbers = new AtomicInteger(0);

    private final static int LIMIT = 100;

    /**
     * 1s出售10台手机
     */
    private RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * 手机最大数量
     */
    private final int limit;

    public TokenBucket() {
        this(LIMIT);
    }

    public TokenBucket(int limit) {
        this.limit = limit;
    }

    public int buy() {
        boolean success = rateLimiter.tryAcquire(10, TimeUnit.SECONDS);
        if (success) {
            if (phoneNumbers.get() >= limit) {
                throw new IllegalStateException("Not any phone can be sale,please wait to next time.");
            }
            int phoneNumber = phoneNumbers.getAndIncrement();
            handleOrder();
            log.info("{} user get the phone:{}", Thread.currentThread(), phoneNumber);
            return phoneNumber;
        } else {
            throw new RuntimeException("Sorry,occur exception when buy phone");
        }
    }

    private void handleOrder() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final TokenBucket tokenBucket = new TokenBucket();
        for (int i = 0; i < 110; i++) {
            new Thread(tokenBucket::buy).start();
        }
    }

}
