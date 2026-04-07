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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Users;
import ceb.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // ✅ Lấy danh sách wishlist
    @GetMapping
    public Object getWishlist() {
        Integer userId = getUserId();

        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("items", wishlistService.getWishlistByUser(userId));
        data.put("count", wishlistService.countWishlistByUser(userId));

        return data;
    }

    // ✅ Thêm sản phẩm vào wishlist
    @PostMapping("/{productId}")
    public Map<String, Object> add(@PathVariable int productId) {

        Integer userId = getUserId();

        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        wishlistService.addIfNotExists(userId, productId);

        Map<String, Object> res = new HashMap<>();
        res.put("message", "Đã thêm vào wishlist");

        return res;
    }

    // ✅ Xóa khỏi wishlist
    @DeleteMapping("/{productId}")
    public Map<String, Object> remove(@PathVariable int productId) {

        Integer userId = getUserId();

        if (userId == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        wishlistService.remove(userId, productId);

        Map<String, Object> res = new HashMap<>();
        res.put("message", "Đã xóa khỏi wishlist");

        return res;
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