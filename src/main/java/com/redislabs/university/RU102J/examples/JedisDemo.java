package com.redislabs.university.RU102J.examples;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        runTransaction(jedis);

        jedis.set("hello", "world");

        System.out.println(jedis.get("hello"));
    }

    public static void runTransaction(Jedis jedis) {
        jedis.set("a", "foo");
        jedis.set("b", "bar");
        jedis.set("c", "baz");
        Transaction t = jedis.multi();

        Response<String> r1 = t.set("b", "1");
//        Response<Long> r2 = t.incr("a");
        Response<String> r3 = t.set("c", "100");

        t.exec();
        r1.get();
//        r2.get();
        r3.get();
    }

}
