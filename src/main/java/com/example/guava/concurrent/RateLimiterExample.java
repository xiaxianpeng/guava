package com.example.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/4 01:19
 * 桶限流
 */
@Slf4j
public class RateLimiterExample {

    /**
     * 限制每s最大0.5次
     */
    private final static RateLimiter rateLimiter = RateLimiter.create(0.5D);

    /**
     * 许可证
     */
    private final static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        /*IntStream.range(0, 10).forEach(i -> {
            executorService.submit(RateLimiterExample::testLimiter);
        });*/

        IntStream.range(0, 10).forEach(i -> {
            executorService.submit(RateLimiterExample::testSemaphore);
        });
    }

    private static void testLimiter() {
        log.info("thread:{} wait:{}", Thread.currentThread(), rateLimiter.acquire());
    }

    private static void testSemaphore() {
        try {
            semaphore.acquire();
            log.info("thread:{} is coming and do work", Thread.currentThread());
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            log.info("thread:{} release the semaphore", Thread.currentThread());
        }
    }
}
