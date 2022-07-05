package com.example.guava.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xianpeng.xia
 * on 2022/7/6 00:31
 */
public class ListenableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // future
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        Integer result = future.get();
        System.out.println(result);

        // ListenableFuture
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        Futures.addCallback(listenableFuture, new ICallBack(), executorService);

        // CompletableFuture
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1000;
        }, executorService);
        completableFuture.whenComplete((v, t) -> {
            System.out.println("I an finished and the result = " + v);
        });

    }

    static class ICallBack implements FutureCallback<Integer> {

        @Override
        public void onSuccess(Integer res) {
            System.out.println("I an finished and the result = " + res);
        }

        @Override
        public void onFailure(Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
