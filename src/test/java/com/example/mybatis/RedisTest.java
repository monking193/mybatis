package com.example.mybatis;

import com.example.mybatis.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testString() {
        redisUtil.set("myTest","你好呀！",300);
        String test = redisUtil.get("myTest").toString();
        System.out.print(test + "\n");
    }

    @Test
    public void testHash() {
        // 批量hash缓存
        Map<String,Object> data = new HashMap<>();
        data.put("key1","value1");
        data.put("key2","value2");
        redisUtil.hmset("hashMapRedisTest",data,300);
        Map<Object,Object> tempData = redisUtil.hmget("hashMapRedisTest");
        String key1 = (String)redisUtil.hget("hashMapRedisTest","key1");

        redisUtil.hset("hashMapTest2","key3","value3",300);
        redisUtil.hset("hashMapTest2","key4","value4",300);
        Map<Object,Object> tempData12 = redisUtil.hmget("hashMapTest2");

    }

    @Test
    public void testList () {
        List<Object> data = new ArrayList<>();
        data.add("1");
        data.add("23");
        redisUtil.lSet("testList",data,300);
        List<Object> dataTemp = redisUtil.lGet("testList",0,-1);

        redisUtil.lSet("testList2","test3",300);
        redisUtil.lSet("testList2","test4",300);
        List<Object> dataTemp12 = redisUtil.lGet("testList2",0,-1);
    }
}
