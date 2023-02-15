package com.xuecheng.content.api;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Queue;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/10/23 17:47
 */
@RestController
@RequestMapping("/redisson")
public class RedissonTestController {
    @Autowired
    RedissonClient redissonClient;

    /**
     * ⼊队
     */
    @GetMapping("/joinqueue")
    public Queue<String> joinqueue(String queuer) {
        RQueue<String> queue001 = redissonClient.getQueue("queue001");
        queue001.add(queuer);
        return queue001;
    }

    /**
     * 出队
     */

    @GetMapping("/removequeue")
    public String removequeue() {
        RQueue<String> queue001 = redissonClient.getQueue("queue001");
        String queuer = queue001.poll();
        return queuer;
    }
}