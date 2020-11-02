package com.cdac.rto.service;

import  com.cdac.rto.domain.User;
import  com.cdac.rto.exception.UserBlockedException;
import java.util.List;


public interface UserService {
    
    public static final Integer LOGIN_STATUS_ACTIVE=1;
    public static final Integer LOGIN_STATUS_BLOCKED=2;
    
    public static final Integer ROLE_ADMIN=1;
    public static final Integer ROLE_USER=2;
    
   
    public void register(User u);
    
    
    public User login(String loginName, String password) throws UserBlockedException;
  
    public List<User> getUserList();
    
   
    public void changeLoginStatus(Integer userId, Integer loginStatus);    
    
 
    public Boolean isUsernameExist(String username);
   
}
