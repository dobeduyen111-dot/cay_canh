package ceb.controller;

import ceb.model.CartItem;
import ceb.service.CartItemService;
import ceb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String showCheckout(Model model) {
        int userId = getUserId();
        List<CartItem> cartItems = cartItemService.getItems(userId);
        double total = cartItemService.getTotal(userId);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "checkout/index";
    }

    @PostMapping("/checkout")
    public String placeOrder(
            @RequestParam String shippingAddress,
            @RequestParam(required = false) String note,
            Model model
    ) {
        int userId = getUserId();
        int orderId = orderService.createOrderFromCart(userId, shippingAddress, note);
        return "redirect:/payment/" + orderId;
    }

    private int getUserId() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();
            if (principal instanceof ceb.model.Users) {
                return ((ceb.model.Users) principal).getUserId();
            }
        } catch (Exception e) {}
        return 1;
    }
}
