package ceb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import ceb.service.UserService;
import ceb.service.WishlistService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserService userService;

    @Autowired
    public WishlistController(WishlistService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }
        @GetMapping
    public String wishlistPage(Model model, Principal principal, HttpSession session) {
        if (principal == null) return "redirect:/login";

        String email = principal.getName();
        int userId = userService.findUserIdByEmail(email);

        model.addAttribute("wishlistItems", wishlistService.getWishlistByUser(userId));
        session.setAttribute("wishlistCount", wishlistService.countWishlistByUser(userId));

        return "wishlist/wishlist";
    }

    @GetMapping("/add/{productId}")
    public String add(@PathVariable("productId") int productId, Principal principal, HttpSession session) {
        if (principal == null) return "redirect:/login";

        String email = principal.getName();
        int userId = userService.findUserIdByEmail(email);

        wishlistService.addIfNotExists(userId, productId);

        session.setAttribute("wishlistCount", wishlistService.countWishlistByUser(userId));

        return "redirect:/product/" + productId;
    }
    @GetMapping("/remove/{productId}")
    public String remove(@PathVariable("productId") int productId, Principal principal) {
        if (principal == null) return "redirect:/login";

        String email = principal.getName();
        int userId = userService.findUserIdByEmail(email);

        wishlistService.remove(userId, productId);

        return "redirect:/wishlist";
    }

}
