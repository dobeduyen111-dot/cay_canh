package ceb.controller;

import ceb.model.Users;
import ceb.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UsersService userService;

    @PostMapping("/register")
    public Object register(@RequestBody Users u, @RequestParam String password) {
        String res = userService.register(u, password);
        if ("EMAIL_EXISTS".equals(res)) {
            return java.util.Map.of("status","error","message","Email đã tồn tại");
        }
        return java.util.Map.of("status","ok","message","Đăng ký thành công");
    }

    @PostMapping("/login")
    public Object login(@RequestParam String email, @RequestParam String password) {
        Users u = userService.login(email, password);
        if (u == null) return java.util.Map.of("status","error","message","Sai tài khoản hoặc mật khẩu");
        return u;
    }
}
