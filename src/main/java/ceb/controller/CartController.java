package ceb.controller;

import ceb.service.CartItemService;
import ceb.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/cart")
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

    @GetMapping
    public String view(Model model, HttpSession session) {
        Integer userId = getUserId();
        if (userId == null) return "redirect:/login";

        model.addAttribute("cartItems", itemService.getItems(userId));
        model.addAttribute("total", itemService.getTotal(userId));

        session.setAttribute("cartCount", itemService.getTotalQuantity(userId));
        return "cart/index";
    }

    @GetMapping(value = {"/add/{productId}", "/add-ajax/{productId}"})
    public String add(@PathVariable int productId, 
                      @RequestParam(name = "quantity", defaultValue = "1") int quantity, 
                      HttpSession session) {
        
        Integer userId = getUserId();
        if (userId == null) return "redirect:/login";

        itemService.addItem(userId, productId, quantity); 
        
        session.setAttribute("cartCount", itemService.getTotalQuantity(userId));
        
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id, HttpSession session) {
        Integer userId = getUserId();
        if (userId == null) return "redirect:/login";

        itemService.remove(id);
        session.setAttribute("cartCount", itemService.getTotalQuantity(userId));
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clear(HttpSession session) {
        Integer userId = getUserId();
        if (userId == null) return "redirect:/login";

        itemService.clear(userId);
        session.setAttribute("cartCount", 0);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateQuantity(@RequestParam int cartItemId,
                                 @RequestParam int quantity) {

        Integer userId = getUserId();
        if (userId == null) return "error";

        itemService.updateQuantity(cartItemId, quantity);
        return "ok";
    }
    
}
