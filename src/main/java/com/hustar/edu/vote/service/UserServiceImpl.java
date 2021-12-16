package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void insertUser(tb_user user) {
        String rowPassword = user.getPassword();    //1234 원문
        String encPassword = encoder.encode(rowPassword);

        user.setPassword(encPassword);
        user.setRole("USER");
        userMapper.save(user);
    }

    @Transactional(readOnly=true)// select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
    public tb_user login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
//        return mapper.login(user.getUsername(), user.getPassword());
    }

    @Transactional(readOnly = true)
    public tb_user searchUser(String username) {
        tb_user user = userMapper.findByUsername(username).orElseGet(()->{
           return new tb_user();
        });
        return user;
    }

    public tb_user getUser(int idx) {
        System.out.println("로그인 구현체");
        return userMapper.getUser(idx);
    }

    public void updateUser(tb_user user) {
        Optional<tb_user> original = userMapper.findByUsername(user.getUsername());

        Date date = new Date();

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

        original.ifPresent(selectUser->{
                selectUser.setNickname(user.getNickname());
                selectUser.setProfile_img(user.getProfile_img());
                selectUser.setSysmoddate(sqlDate);
            userMapper.save(selectUser);
        });
    }
}
