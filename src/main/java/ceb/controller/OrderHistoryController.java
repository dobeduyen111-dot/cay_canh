package ceb.controller;

import ceb.model.Users;
import ceb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderHistoryController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/my-orders")
    public String myOrders(Model model) {
        int userId = getUserId(); 
        model.addAttribute("orders", orderService.getUserOrders(userId));
        return "user/my-orders"; 
    }
    private int getUserId() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();
            if (principal instanceof Users) {
                return ((Users) principal).getUserId();
            }
        } catch (Exception e) {}
        return 0; 
    }
}