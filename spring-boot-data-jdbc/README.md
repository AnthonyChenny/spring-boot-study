### springboot操作原生的jdbc
1. pom文件导入
```xml
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
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
4.配置druid数据源的监控<br>
在写配置类的时候,本项目使用的是2.2.1版本的springboot,好像是必需要引入log4j,spring.datasource下的属性才会生效,不然会报错
<strong>Failed to bind properties under 'spring.datasource' to javax.sql.DataSource</strong>,这个问题还有待验证//TODO
```java
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
```
5.数据源登录地址<br>
http://localhost:8080/druid/login.html
账号和密码在上面的配置类中可以设置
