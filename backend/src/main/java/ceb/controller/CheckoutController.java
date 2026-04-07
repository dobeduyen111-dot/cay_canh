package ceb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.CartItem;
import ceb.model.Users;
import ceb.service.CartItemService;
import ceb.service.OrderService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    // ✅ Lấy thông tin checkout
    @GetMapping
    public Map<String, Object> getCheckoutInfo() {
        Integer userId = getUserId();

        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        List<CartItem> cartItems = cartItemService.getItems(userId);
        double total = cartItemService.getTotal(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("items", cartItems);
        data.put("total", total);

        return data;
    }

    // ✅ Đặt hàng
    @PostMapping
    public Map<String, Object> placeOrder(@RequestBody Map<String, String> request) {

        Integer userId = getUserId();

        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        String shippingAddress = request.get("shippingAddress");
        String note = request.get("note");

        int orderId = orderService.createOrderFromCart(userId, shippingAddress, note);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đặt hàng thành công");
        response.put("orderId", orderId);

        return response;
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