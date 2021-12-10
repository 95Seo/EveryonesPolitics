package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.Calendar;
import com.hustar.edu.vote.mapper.CalenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalenService {

    @Autowired(required = false)
    private CalenDao dao;

    public ArrayList<Calendar> calenList(){
        return dao.calenList();
    }
}
