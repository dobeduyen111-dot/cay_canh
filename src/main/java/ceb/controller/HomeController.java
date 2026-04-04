package ceb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ceb.model.Users;
import ceb.service.ProductsService;

@Controller 
public class HomeController { 
    @Autowired
    private ProductsService productService;
    @GetMapping("/")
    public String index(Model model) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null && auth.isAuthenticated()
            && !"anonymousUser".equals(auth.getPrincipal())) {

        Users user = (Users) auth.getPrincipal();
        model.addAttribute("loggedName", user.getFullName());
    }
    model.addAttribute("CayCanh", productService.getByCategoryLimit(1, 5));
    model.addAttribute("ChauCay", productService.getByCategoryLimit(2, 5));
    model.addAttribute("phuKien", productService.getByCategoryLimit(3, 5));
    return "home/index";
    }
}
