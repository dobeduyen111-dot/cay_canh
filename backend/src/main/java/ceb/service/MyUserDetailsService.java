package ceb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ceb.model.Users;
import ceb.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Gọi hàm findByUsername vừa tạo trong Repository
        Users appUser = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

        // Chuyển đổi Users entity sang UserDetails của Spring Security
        return User.builder()
                .username(appUser.getEmail()) // Dùng Email làm username đăng nhập
                .password(appUser.getPassword())
                .authorities(new ArrayList<>()) // Bạn có thể thêm roles/authorities ở đây sau
                .build();
    }
}