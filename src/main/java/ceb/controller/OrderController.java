package ceb.controller;

import ceb.model.Orders;
import ceb.model.Users;
import ceb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/history")
    public String viewOrderHistory(Model model) {
        int userId = getUserId(); 
        List<Orders> myOrders = orderService.getOrdersByUserId(userId);
    
        model.addAttribute("orders", myOrders);
        
        return "order/history"; 
    }
    private int getUserId() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();
            if (principal instanceof Users) {
                return ((Users) principal).getUserId();
            }
        } catch (Exception e) {

        }
        return 0;
    }
}