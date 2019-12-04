/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MybatisConfig
 * Author:   Anthony
 * Date:     2019/12/4 22:26
 * Description: mybatis的配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anthony.springboot.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
         return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}

/**
 * 〈一句话功能简述〉<br> 
 * 〈mybatis的配置类〉
 *
 * @author Anthony
 * @create 2019/12/4
 * @since 1.0.0
 */