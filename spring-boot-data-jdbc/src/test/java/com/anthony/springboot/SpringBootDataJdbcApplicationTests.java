package com.anthony.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringBootDataJdbcApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        //class com.zaxxer.hikari.HikariDataSource
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //HikariProxyConnection@108209958 wrapping com.mysql.cj.jdbc.ConnectionImpl@474821de
        connection.close();
    }

}
