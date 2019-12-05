### spingboot整合jpa
1.pom文件中要导入jpa的jar包
```xml
<!--        热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
        <!--        数据源druid-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.21</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
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
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
```
2.新建entity实体类
```java
@Data
@Entity//告诉jpa这是一个实体类(和数据表映射的类)
@Table(name = "tbl_user")//用来指定和哪个表的数据对应;如果省略默认就是user表
public class User {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int id;
    @Column(name = "user_name",length = 50)//和数据表对应的列
    private String username;
    @Column//省略默认email就是列名
    private String email;
}
```
注解说明:
-  @Entity//告诉jpa这是一个实体类(和数据表映射的类)
-  @Table(name = "tbl_user")  指定该类和数据的对应表示tbl_user,如果不加name属性默认就是class类名的小写
-  @Id  声明该字段为主键
-  @GeneratedValue(strategy = GenerationType.IDENTITY)  主键自增
-  @Column(name = "user_name",length = 50)    和数据表对应的列,如果省略了默认就是属性名
3. 新建UserResposity接口<br>
```java
//UserRepository相当于Dao层,需要继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User, Integer> {
}
```
不需要写方法,底层已经实现了crud的方法<br>
4. 配置jpa的yml文件
```xml
jpa:
    hibernate:
#      更新或者创建数据库的表结构
      ddl-auto: update
#      控制台显示sql
    show-sql: true
```
5. 新建controller测试
```java
@Autowired
    private UserRepository userRepository;

    @ResponseBody
    @GetMapping(value = "/user/{id}")
    private User getUser(@PathVariable("id")Integer id) {

        return userRepository.getOne(id);
    }
```
6. 注意点<br>
由于Entity实体类使用了lombok插件,导致序列化出现错误
```java
com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: com.anthony.springboot.entity.User$HibernateProxy$sBWkbglw["hibernateLazyInitializer"])
```
所以要在实体类上面加上<strong>@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})</strong>