package ceb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceb.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public int findUserIdByEmail(String email) {
        Integer id = userRepo.getUserIdByEmail(email);
        return (id != null) ? id : -1;
    }
}
