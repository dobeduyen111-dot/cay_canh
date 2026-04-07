package ceb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Users;
import ceb.service.CartItemService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemService itemService;

    private Integer getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()
                || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }

        Users user = (Users) auth.getPrincipal();
        return user.getUserId();
    }

    // ✅ Xem giỏ hàng
    @GetMapping
    public Map<String, Object> viewCart() {
        Integer userId = getUserId();

        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("items", itemService.getItems(userId));
        data.put("total", itemService.getTotal(userId));
        data.put("totalQuantity", itemService.getTotalQuantity(userId));

        return data;
    }

    // ✅ Thêm sản phẩm vào giỏ
    @PostMapping("/add")
    public String addToCart(@RequestParam int productId,
                            @RequestParam(defaultValue = "1") int quantity) {

        Integer userId = getUserId();
        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        itemService.addItem(userId, productId, quantity);
        return "Đã thêm vào giỏ hàng";
    }

    // ✅ Xóa 1 item
    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable int id) {

        Integer userId = getUserId();
        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        itemService.remove(id);
        return "Đã xóa sản phẩm";
    }

    // ✅ Xóa toàn bộ giỏ
    @DeleteMapping("/clear")
    public String clearCart() {

        Integer userId = getUserId();
        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        itemService.clear(userId);
        return "Đã xóa toàn bộ giỏ hàng";
    }

    // ✅ Update số lượng
    @PutMapping("/update")
    public String updateQuantity(@RequestParam int cartItemId,
                                 @RequestParam int quantity) {

        Integer userId = getUserId();
        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        itemService.updateQuantity(cartItemId, quantity);
        return "Cập nhật thành công";
    }
}