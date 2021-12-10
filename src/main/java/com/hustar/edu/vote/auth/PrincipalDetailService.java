package com.hustar.edu.vote.auth;

import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 함.
    // username이 DB에 있는지만 확인해 주면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        tb_user principal = mapper.findByUsername(username)
                .orElseThrow(()->{
                   return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+username);
                });
        System.out.println("사용자를 찾았습니다 : " + username);
        return new PrincipalDetail(principal);
    }
}
