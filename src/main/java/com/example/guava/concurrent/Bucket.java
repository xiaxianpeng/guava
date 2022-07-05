package com.example.guava.concurrent;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/5 22:55
 * 桶限流
 */

@Slf4j(topic = "Bucket")
public class Bucket {

    private final ConcurrentLinkedQueue<Integer> container = new ConcurrentLinkedQueue<>();

    private final static int BUCKET_LIMIT = 1000;

    private final RateLimiter rateLimiter = RateLimiter.create(10);

    private final Monitor offerMonitor = new Monitor();

    private final Monitor pollMonitor = new Monitor();

    public void submit(Integer data) {

        if (offerMonitor.enterIf(offerMonitor.newGuard(() -> container.size() < BUCKET_LIMIT))) {

            try {
                container.offer(data);
                log.info("{} submit data:{},current size:{}", Thread.currentThread(), data, container.size());
            } finally {
                offerMonitor.leave();
            }
        } else {
            throw new IllegalStateException("The bucket is full.");
        }
    }

    public void takeThenConsume(Consumer<Integer> consumer) {
        if (pollMonitor.enterIf(pollMonitor.newGuard(() -> !container.isEmpty()))) {

            try {
                log.info("{} waiting {}", Thread.currentThread(), rateLimiter.acquire());
                consumer.accept(container.poll());
            } finally {
                pollMonitor.leave();
            }
        }
    }

    public static void main(String[] args) {
        final Bucket bucket = new Bucket();
        final AtomicInteger DATA_CREATOR = new AtomicInteger(0);
        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                for (; ; ) {
                    int data = DATA_CREATOR.getAndIncrement();
                    bucket.submit(data);
                    try {
                        TimeUnit.MILLISECONDS.sleep(200L);
                    } catch (IllegalStateException e) {
                        log.error("error:{}", e.getMessage());
                    } catch (Exception e) {

                    }
                }
            }).start();
        });

        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                for (; ; ) {
                    bucket.takeThenConsume(x -> log.info("{} w x:{}", Thread.currentThread(), x));
                }
            }).start();
        });
    }
}
