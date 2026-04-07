package ceb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceb.model.Users;
import ceb.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // ✅ lấy user theo email (quan trọng cho login)
    public Users findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
    }

    // ✅ lấy userId
    public Integer findUserIdByEmail(String email) {
        return userRepo.getUserIdByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy userId"));
    }
}