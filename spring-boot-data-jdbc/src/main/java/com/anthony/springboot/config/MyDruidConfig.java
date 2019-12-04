/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyDuidConfig
 * Author:   Anthony
 * Date:     2019/12/4 14:14
 * Description: 数据源配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anthony.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyDruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")//会读取application.yml文件中的所有属性
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置Druid监控
    //1.配置管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,Object> intitparams = new HashMap<>();
        intitparams.put("loginUsername","admin");
        intitparams.put("loginPassword","123456");
        intitparams.put("allow","");//默认是允许所有访问
        servletRegistrationBean.setInitParameters(intitparams);
        return servletRegistrationBean;
    }
    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String,Object> intitparams = new HashMap<>();
        //TODO  和springmvc的拦截器做比较
        intitparams.put("exclusions","*.js,*.css,/druid/*");//排除某些不拦截的,如静态资源
        filterRegistrationBean.setInitParameters(intitparams);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据源配置类〉
 *
 * @author Anthony
 * @create 2019/12/4
 * @since 1.0.0
 */