package com.valid.english.utils;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

public class RedissonManager {

    private static RedissonManager instance;

    private static class HolderClass {
        private static RedissonManager redissUtils = new RedissonManager();
    }

    public static RedissonManager getInstance() {
        if(instance == null) {
            instance = HolderClass.redissUtils;
        }
        return instance;
    }

    public RedissonClient getRedisson(Config config) {
        RedissonClient client = Redisson.create(config);
        return client;
    }

    public RedissonClient getRedisson(String ip, int port) {
        Config config = new Config();
        config.useSingleServer().setAddress(ip + ":" + port);
        RedissonClient client = Redisson.create(config);
        return client;
    }

    public void closeRedisson(RedissonClient redisson) {
        if(redisson != null) {
            redisson.shutdown();
        }
    }

    /**
     * 获得字符串对象
     * @param redisson
     * @param key
     * @param <T>
     * @return
     */
    public <T> RBucket<T> getRBuket(RedissonClient redisson, String key) {
        RBucket<T> bucket = null;
        if(redisson != null) {
            bucket = redisson.getBucket(key);
        }
        return bucket;
    }

    /**
     * 获取Map对象
     * @param redisson
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> RMap<K, V> getRMap(RedissonClient redisson, String key) {
        RMap<K, V> rMap = null;
        if(redisson != null) {
            rMap = redisson.getMap(key);
        }
        return rMap;
    }

    /**
     * 获取SortedSet对象
     * @param redisson
     * @param key
     * @param <V>
     * @return
     */
    public <V> RSortedSet<V> getRSortedSet(RedissonClient redisson, String key) {
        RSortedSet<V> sortedSet = null;
        if(redisson != null) {
            sortedSet = redisson.getSortedSet(key);
        }
        return sortedSet;
    }

    /**
     * 获取Set对象
     * @param redisson
     * @param key
     * @param <V>
     * @return
     */
    public <V> RSet<V> getRSet(RedissonClient redisson, String key) {
        RSet<V> set = null;
        if(redisson != null) {
            set = redisson.getSet(key);
        }
        return set;
    }

    /**
     * 获取list对象
     * @param redisson
     * @param key
     * @return
     */
    public <V> RList<V> getRList(RedissonClient redisson,String key){
        RList<V> rList = null;
        if(redisson != null) {
            rList = redisson.getList(key);
        }
        return rList;
    }

    /**
     * 获取Queue对象
     * @param redisson
     * @param key
     * @return
     */
    public <V> RQueue<V> getRQueue(RedissonClient redisson,String key){
        RQueue<V> rQueue = null;
        if(redisson != null) {
            rQueue = redisson.getQueue(key);
        }
        return rQueue;
    }

    /**
     * 获取 RDeque 对象
     * @param redisson
     * @param key
     * @return
     */
    public <V> RDeque<V> getRDueue(RedissonClient redisson,String key){
        RDeque<V> rDeque = null;
        if(redisson != null) {
            rDeque = redisson.getDeque(key);
        }
        return rDeque;
    }

    /**
     * 获得锁
     * @param redisson
     * @param key
     * @return
     */
    public RLock getLock(RedissonClient redisson,String key){
        RLock lock = null;
        if(redisson != null) {
            lock = redisson.getLock(key);
        }
        return lock;
    }

    /**
     * 获取消息的Topic
     * @param redisson
     * @param key
     * @return
     */
    public RTopic getRTopic(RedissonClient redisson,String key){
        RTopic rTopic= null;
        if(redisson != null) {
            rTopic = redisson.getTopic(key);
        }
        return rTopic;
    }


}
