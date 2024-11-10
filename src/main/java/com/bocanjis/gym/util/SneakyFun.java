package com.bocanjis.gym.util;

import lombok.SneakyThrows;

import java.util.function.Function;

public interface SneakyFun<T, R> extends Function<T, R> {

    R sneaky(T t) throws Exception;

    default R apply(T t) {
        try {
            return sneaky(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
