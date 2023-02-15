package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.po.CoursePublish;

import java.io.File;

public interface CoursePublishService {
    /**
     * 获取课程预览信息
     *
     * @param courseId 课程id
     * @return
     */
    public CoursePreviewDto getCoursePreviewInfo(Long courseId);

    public void commitAudit(Long companyId, Long courseId);

    public void publish(Long companyId, Long courseId);

    /**
     * @param courseId 课程id
     * @return File 静态化文件
     * @description 课程静态化
     */
    public File generateCourseHtml(Long courseId);

    /**
     * @param file 静态化文件
     * @return void
     * @description 上传课程静态化页面
     */
    public void uploadCourseHtml(Long courseId, File file);

    public Boolean saveCourseIndex(Long courseId);

    public CoursePublish getCoursePublish(Long courseId);

    public CoursePublish getCoursePublishCache(Long courseId);
}
