package com.sawebb.services;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class Redis {

    public static StatefulRedisConnection<String, String> getConnection() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        return connection;
    }
}
