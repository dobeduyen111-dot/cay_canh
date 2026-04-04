package ceb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ceb.model.Users;
import ceb.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Users());
        return "auth/register";
    }

    @GetMapping("/login")
    public String showLoginForm( ) {
   
        return "auth/login";
    }

    @PostMapping("/register")
    public String register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);

        if (user.getRole() == null) {
            user.setRole("USER");
        }

        repo.save(user);
        return "redirect:/login";
    }

     @GetMapping("/error/403")
    public String showError403() {

        return "error/403";
    }
}

