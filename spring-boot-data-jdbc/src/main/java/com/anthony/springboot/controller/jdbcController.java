/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: jdbcController
 * Author:   Anthony
 * Date:     2019/12/4 10:14
 * Description: jdbc
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anthony.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class jdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping(value = "/emp/query")
    public Map<String,Object> map() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from employee");
        return maps.get(1);
    }
}

/**
 * 〈一句话功能简述〉<br> 
 * 〈jdbc〉
 *
 * @author Anthony
 * @create 2019/12/4
 * @since 1.0.0
 */