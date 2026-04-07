package ceb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Users;
import ceb.service.ProductsService;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private ProductsService productService;

    @GetMapping
    public Map<String, Object> getHomeData() {

        Map<String, Object> data = new HashMap<>();

        // 🔐 lấy user nếu có
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()
                && !"anonymousUser".equals(auth.getPrincipal())) {

            Users user = (Users) auth.getPrincipal();
            data.put("loggedName", user.getFullName());
        }

        // 📦 dữ liệu sản phẩm theo category
        data.put("cayCanh", productService.getByCategoryLimit(1, 5));
        data.put("chauCay", productService.getByCategoryLimit(2, 5));
        data.put("phuKien", productService.getByCategoryLimit(3, 5));

        return data;
    }
}