package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.TeachplanMedia;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/10/10 14:51
 */
public interface TeachplanService {

    public List<TeachplanDto> findTeachplanTree(Long courseId);

    /**
     * @param dto
     * @return void
     * @description 保存课程计划(新增 / 修改)
     * @author Mr.M
     * @date 2022/10/10 15:07
     */
    public void saveTeachplan(SaveTeachplanDto dto);

    /**
     * 教学计划绑定媒资
     *
     * @param bindTeachplanMediaDto
     * @return
     */
    public TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);

}
