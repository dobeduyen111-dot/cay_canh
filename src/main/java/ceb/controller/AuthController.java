package ceb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ceb.model.Users;
import ceb.repository.UsersRepository;

@Controller
public class AuthController {

    @Autowired
    private UsersRepository usersRepository;

    // ---------- LOGIN FORM ----------
    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }

    // ---------- LOGIN FORM SUBMIT ----------
    @PostMapping("/auth/login")
    public String loginProcess(
            @RequestParam String email,
            @RequestParam String password) {

        // Kiểm tra email trong DB
        Users user = usersRepository.findByEmailLegacy(email);
        if (user == null) {
            return "redirect:/auth/login?error=email";
        }

        // Hash mật khẩu người dùng nhập để đối chiếu
        byte[] inputHash = Users.hashPassword(password);   // bạn cần hàm này trong model
        byte[] dbHash = user.getPassword();

        if (!java.util.Arrays.equals(inputHash, dbHash)) {
            return "redirect:/auth/login?error=password";
        }

        return "redirect:/auth?success";
    }

    // ---------- GOOGLE LOGIN CALLBACK ----------
    @GetMapping("/auth/googleresponse")
    public String googleResponse(@AuthenticationPrincipal OAuth2User oauthUser) {

        Map<String, Object> map = oauthUser.getAttributes();

        String googleId = map.get("sub").toString();
        String email = map.get("email").toString();
        String givenName = map.get("given_name").toString();
        String familyName = map.get("family_name").toString();
        String fullName = map.get("name").toString();

        // Gọi repository để lưu hoặc cập nhật người dùng Google
        String role = usersRepository.saveOrUpdateGoogleUser(
                googleId,
                email,
                givenName,
                familyName,
                fullName
        );

        // Điều hướng theo vai trò
        if ("Admin".equals(role)) {
            return "redirect:/admin/dashboard";
        }

        return "redirect:/auth?success";
    }

    // ---------- TRANG CHÍNH SAU LOGIN ----------
    @GetMapping("/auth")
    public String index() {
        return "auth/index";
    }
}
