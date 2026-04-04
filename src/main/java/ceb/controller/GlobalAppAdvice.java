package ceb.controller;

import ceb.model.Users;
import ceb.service.CartItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAppAdvice {
    @Autowired
    private CartItemService cartItemService;
    @ModelAttribute
    public void addLoggedName(Model model, Authentication auth) {

        if (auth != null &&
            auth.isAuthenticated() &&
            !"anonymousUser".equals(auth.getPrincipal())) {

            if (auth.getPrincipal() instanceof Users user) {
                model.addAttribute("loggedName", user.getFullName());
            }
        }
    }
    @ModelAttribute("globalCartCount")
    public Integer globalCartCount(HttpSession session, Authentication auth) {
        if (auth != null &&
            auth.isAuthenticated() &&
            !"anonymousUser".equals(auth.getPrincipal())) {

            Users user = (Users) auth.getPrincipal();
            return cartItemService.getTotalQuantity(user.getUserId());
        }
        Integer cartCount = (Integer) session.getAttribute("cartCount");
        return (cartCount != null) ? cartCount : 0;
    }
}
