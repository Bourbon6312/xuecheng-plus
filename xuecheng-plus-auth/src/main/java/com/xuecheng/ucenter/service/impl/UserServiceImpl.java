package com.xuecheng.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.ucenter.mapper.XcMenuMapper;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcMenu;
import com.xuecheng.ucenter.model.po.XcUser;
import com.xuecheng.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    XcUserMapper xcUserMapper;

    @Autowired
    XcMenuMapper xcMenuMapper;

    @Autowired
    ApplicationContext applicationContext;


//    /**
//     * 根据账号查询用户信息
//     *
//     * @param s
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, s));
//        if (user == null) {
//            //返回空表示用户不存在
//            return null;
//        }
//
//        //取出数据库存储的正确密码
//        String password = user.getPassword();
//        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
//        String[] authorities = {"p1"};
//        //为了安全在令牌中不放密码
//        user.setPassword(null);
//        //将user对象转json
//        String userString = JSON.toJSONString(user);
//        //创建UserDetails对象
//        UserDetails userDetails = User.withUsername(userString).password(password).authorities(authorities).build();
//
//        return userDetails;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AuthParamsDto authParamsDto = null;
        try {
            //将认证参数转为AuthParamsDto类型
            authParamsDto = JSON.parseObject(s, AuthParamsDto.class);
        } catch (Exception e) {
            log.info("认证请求不符合项目要求:{}", s);
            throw new RuntimeException("认证请求数据格式不对");
        }

        //认证方法
        String authType = authParamsDto.getAuthType();
        AuthService authService = applicationContext.getBean(authType + "_authservice", AuthService.class);
        XcUserExt user = authService.execute(authParamsDto);

        return getUserPrincipal(user);
    }


    /**
     * 查询用户信息
     *
     * @param user
     * @return
     */
    public UserDetails getUserPrincipal(XcUserExt user) {
        //查询用户权限
        List<XcMenu> xcMenus = xcMenuMapper.selectPermissionByUserId(user.getId());

        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        List<String> list = new ArrayList<>();
        xcMenus.forEach(xcMenu -> {
            list.add(xcMenu.getCode());
        });

        String[] authorities = {"test"};
        if (list.size() > 0) authorities = list.toArray(new String[0]);

        String password = user.getPassword();
        //为了安全在令牌中不放密码
        user.setPassword(null);
        //将user对象转json
        String userString = JSON.toJSONString(user);
        //创建UserDetails对象
//        UserDetails userDetails = User.withUsername(userString).password(password).authorities(authorities).build();
        UserDetails userDetails = User.withUsername(userString).password("").authorities(authorities).build();
        return userDetails;
    }

}
