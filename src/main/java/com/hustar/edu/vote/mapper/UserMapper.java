package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.tb_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Mapper
public interface UserMapper extends JpaRepository<tb_user, Integer> {
    // JPA Naming 전략
    // SELECT * FROM user WHERE username = ? AND password = ? ; 가 작동한다.
    tb_user findByUsernameAndPassword(String username, String password);

    Optional<tb_user> findByUsername(String username);

    @Query(value = "SELECT * FROM tb_user WHERE idx = ?1", nativeQuery = true)
    tb_user getUser(int idx);

//    int updateUser(@Param("user") tb_user user);
}