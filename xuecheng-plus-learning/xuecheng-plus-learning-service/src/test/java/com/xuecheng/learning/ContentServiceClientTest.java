package com.xuecheng.learning;

import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.learning.feignclient.ContentServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Mr.M
 * @version 1.0
 * @description 内容管理远程接口测试
 * @date 2022/10/25 9:16
 */
@SpringBootTest
public class ContentServiceClientTest {

    @Autowired
    ContentServiceClient contentServiceClient;

    @Test
    public void testGetCoursepublish() {
        CoursePublish coursepublish = contentServiceClient.getCoursepublish(18L);
        //断言coursepublish不为空
        Assertions.assertNotNull(coursepublish);
    }

}
