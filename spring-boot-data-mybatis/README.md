### springboot整合mybatis
1.引入pom文件
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.21</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.1</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```
2.编写mapper
```java
@Mapper//指定操作数据库的mapper
public interface DepartmentMapper {

    @Select("select * from anthony_department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from anthony_department where id=#{id}")
    public int deleteById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into anthony_department(departmentName) values (#{departmentName})")
    public int addDepartment(Department department);

    @Update("update anthony_department set departmentName=#{departmentName} where id=#{id}")
    public int updateDepartment(Department department);
}
```
说明:@Options(useGeneratedKeys = true,keyProperty = "id")
告诉mybatis是不是使用自动生成的主键,并将id属性封装到department,返回的时候会带上该属性
```json
{
"id": 6,
"departmentName": "技术部"
}
```
3.配置mybatis的配置类<br>
开启驼峰命名
```java
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
```
4.使用xml映射文件<br>
一般在resource文件夹下创建xml文件<br>
在ideal中xml会被认为是资源文件,所以一般放在resource文件下;如果放在resource文件夹下,mybatis的注册映射文件就要改为classpath:XXX.xml;如果是放在dao包下,那么需要在pom文件中注册dao包下的mybatis文件为资源目录
```xml
<!-- 注册dao包下mybatis文件为资源目录 -->
<resource>
    <directory>src/main/java</directory>
    <includes>
        <include>**/*.xml</include>
    </includes>
</resource>
```
配置类mybatis-config.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
<!--        设置驼峰命名-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```
高速yml文件配置类和映射文件的位置
```xml
# 全局配置文件
# 注册映射文件地址
mybatis:
  config-location: classpath:mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml
```