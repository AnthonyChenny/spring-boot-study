/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserController
 * Author:   Anthony
 * Date:     2019/12/5 10:07
 * Description: 用户处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anthony.springboot.controller;

import com.anthony.springboot.entity.User;
import com.anthony.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @GetMapping(value = "/user/{id}")
    private User getUser(@PathVariable("id")Integer id) {

        return userRepository.getOne(id);
    }

    @ResponseBody
    @GetMapping(value = "/user")
    public User addUser(User user) {
        User save = userRepository.save(user);
        return  save;
    }
}

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户处理器〉
 *
 * @author Anthony
 * @create 2019/12/5
 * @since 1.0.0
 */