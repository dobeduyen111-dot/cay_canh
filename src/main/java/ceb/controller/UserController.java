package ceb.controller;

import ceb.model.Products;
import ceb.service.ProductsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class UserController {
    @Autowired
    private ProductsService service;
    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.all());
        return "product/product";
    }
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Products> results = service.search(keyword);
        model.addAttribute("results", results);
        model.addAttribute("keyword", keyword);
        return "product/search";
    }
    
    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        Products p = service.get(id);

        if (p == null) {
            return "redirect:/product"; 
        }

        model.addAttribute("product", p);
        return "product/detail";
    }
    @GetMapping("/cay")
    public String cay(Model model) {
        model.addAttribute("products", service.getProductsByCategory(1));
        model.addAttribute("title", "CÂY CẢNH");
        return "product/cay";
    }

    @GetMapping("/chau")
    public String chau(Model model) {
        model.addAttribute("products", service.getProductsByCategory(2));
        model.addAttribute("title", "CHẬU CÂY");
        return "product/chau";
    }

    @GetMapping("/phukien")
    public String phuKien(Model model) {
        model.addAttribute("products", service.getProductsByCategory(3));
        model.addAttribute("title", "PHỤ KIỆN CÂY CẢNH");
        return "product/phukien";
    }

}