package com.asu.ser515.team20.mathboard.service;

import com.asu.ser515.team20.mathboard.dao.ExcelReader;
import com.asu.ser515.team20.mathboard.dao.ExcelWriter;
import com.asu.ser515.team20.mathboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private ExcelReader excelReader;

    @Autowired
    private ExcelWriter excelWriter;

    public boolean addUser(User user){
        return excelWriter.addUser(user);
    }

    public User getUsers(String userId, String pass) {
       return excelReader.searchUser(userId, pass);
    }

    public User getUsersforDelete(String userId) {
        return excelReader.searchUserForDelete(userId);
    }

    public boolean deleteUser(String userId){
        return excelWriter.DeleteUser(userId);
    }
}
