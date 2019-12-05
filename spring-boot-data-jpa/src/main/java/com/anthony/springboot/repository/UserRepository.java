package com.anthony.springboot.repository;

import com.anthony.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//UserRepository相当于Dao层,需要继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User, Integer> {
}
