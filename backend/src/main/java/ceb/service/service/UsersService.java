package ceb.service.service;

import java.util.List;

import ceb.domain.entity.Users;



public interface UsersService{
    List<Users> findAll();
 
    Users getUsersByEmail(String email);
    
}


