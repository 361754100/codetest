package com.valid.english;

import com.valid.english.factory.BeanFactory;
import com.valid.english.service.HotelService;
import com.valid.english.service.PersonService;
import com.valid.english.utils.RedissonManager;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * author: Jianzhang Mo
 * date: 2020-03-09
 * desc: code test
 */

public class Main {

    public static void main(String args[]) {
        BeanFactory.getInstance().loadBean("com.valid.english");

//        PersonService personService = (PersonService) BeanFactory.getInstance().getBean(PersonService.class);
//        personService.test();

        HotelService hotelService = (HotelService) BeanFactory.getInstance().getBean(HotelService.class);
        hotelService.order();
    }

    public static void testRLock() {
        RedissonManager redissonManager = RedissonManager.getInstance();
        Config config = new Config();
        config.useSingleServer().setAddress("192.168.3.247:6037");
        // 设置看门狗 30秒延长一次
        config.setLockWatchdogTimeout(30000l);

        RedissonClient redisson = redissonManager.getRedisson(config);
        RLock lock = redissonManager.getLock(redisson, "locktest");
        try {
            lock.tryLock(0, 30l, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
