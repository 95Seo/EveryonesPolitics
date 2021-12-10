package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.tb_user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Mapper
public interface UserMapper extends JpaRepository<tb_user, Integer> {
    // JPA Naming 전략
    // SELECT * FROM user WHERE username = ? AND password = ? ; 가 작동한다.
    tb_user findByUsernameAndPassword(String username, String password);

    Optional<tb_user> findByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    tb_user login(String username);

    @Query(value = "UPDATE user SET nickname=?1, update_date=now() WHERE username = ?3", nativeQuery = true)
    void updateUser(String nickname, String username);
}