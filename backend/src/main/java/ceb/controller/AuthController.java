package ceb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Users;
import ceb.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // ✅ REGISTER
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Users user) {

        Map<String, Object> response = new HashMap<>();

        // encode password
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);

        if (user.getRole() == null) {
            user.setRole("USER");
        }

        repo.save(user);

        response.put("message", "Đăng ký thành công");
        return response;
    }

    // ✅ LOGIN (basic - chưa JWT)
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Users user) {

        Map<String, Object> response = new HashMap<>();

        Optional<Users> optionalUser = repo.findByEmail(user.getUsername());

        if (optionalUser.isEmpty()) {
            response.put("error", "User không tồn tại");
            return response;
        }

        Users dbUser = optionalUser.get();

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            response.put("error", "Sai mật khẩu");
            return response;
        }

        response.put("message", "Login thành công");
        response.put("user", dbUser);

        return response;
    }
}