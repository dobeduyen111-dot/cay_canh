package ceb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Orders;
import ceb.model.Users;
import ceb.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ Lịch sử đơn hàng của user
    @GetMapping("/my")
    public List<Orders> getMyOrders() {

        Integer userId = getUserId();

        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        return orderService.getOrdersByUserId(userId);
    }

    // 🔐 lấy user từ security
    private Integer getUserId() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !auth.isAuthenticated()
                    || "anonymousUser".equals(auth.getPrincipal())) {
                return null;
            }

            Users user = (Users) auth.getPrincipal();
            return user.getUserId();

        } catch (Exception e) {
            return null;
        }
    }
}