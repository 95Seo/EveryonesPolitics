package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.tb_user;

public interface UserService {
    public void insertUser(tb_user user);
    public tb_user login(String username, String password);
    public tb_user searchUser(String username);
    public tb_user login(String username);
    public void updateUser(String nickname, String username);
}
