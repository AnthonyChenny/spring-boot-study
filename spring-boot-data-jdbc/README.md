### springboot操作原生的jdbc
1. pom文件导入
```xml
 <!-- 引入Spring封装的jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- 引入Spring Boot 测试模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```
2. 自动运行sql文件<br>
在resource目录下新建schemea.sql或者schema-all.sql文件,在启动项目后会自动运行sql文件中的sql语句;
可以将建表语句放在里面;<br>
记得运行完项目后要将该文件删除,避免第二次启动项目又运行了该文件
3. 使用JdbcTemplate操作数据库
```java
 List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from employee");
```
