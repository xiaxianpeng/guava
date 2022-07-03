package com.example.guava.eventbus.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xianpeng.xia
 * on 2022/7/3 16:00
 * 注册表
 *
 * topic -> List<subscriber-subscribe>
 */
public class Registry {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer
        = new ConcurrentHashMap<>();

    /**
     * 绑定
     */
    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> {
            tierSubscriber(subscriber, m);
        });
    }


    /**
     * 解绑
     */
    public void unbind(Object subscriber) {
        subscriberContainer.forEach((key, queue) -> {
            queue.forEach(s -> {
                if (s.getSubscribeObject() == subscriber) {
                    s.setDisable(true);
                }
            });
        });
    }

    /**
     * 查找
     */
    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(final String topic) {
        return subscriberContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method method) {
        final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();

        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                .filter(m -> m.isAnnotationPresent(Subscribe.class)
                    && m.getParameterCount() == 1
                    && m.getModifiers() == Modifier.PUBLIC)
                .forEach(methods::add);

            temp = temp.getSuperclass();
        }
        return methods;
    }
}
