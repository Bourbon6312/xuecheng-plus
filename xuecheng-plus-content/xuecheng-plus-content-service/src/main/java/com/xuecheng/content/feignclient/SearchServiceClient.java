package com.xuecheng.content.feignclient;

import com.xuecheng.content.config.MultipartSupportConfig;
import com.xuecheng.content.feignclient.model.CourseIndex;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mr.M
 * @version 1.0
 * @description 搜索服务远程调用接口
 * @date 2022/10/19 9:24
 */
@FeignClient(value = "search", fallbackFactory = SearchServiceClientFallbackFactory.class)
@RequestMapping("/search")
public interface SearchServiceClient {

    @PostMapping("/index/course")
    public Boolean add(@RequestBody CourseIndex courseIndex);

}
