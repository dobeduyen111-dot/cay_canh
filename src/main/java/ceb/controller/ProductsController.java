package ceb.controller;

import ceb.model.Products;
import ceb.service.CategoriesService;
import ceb.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/product")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.all());
        return "admin/product/index";
    }

    @Autowired
    private CategoriesService categoryService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Products());
        model.addAttribute("categories", categoryService.all());
        return "admin/product/add";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute Products product) {

        if (product.getCategoryId() == 0) {
            return "redirect:/admin/product/add?error=category";
        }

        service.add(product);
        return "redirect:/admin/product?success";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("product", service.get(id));
        return "admin/product/edit";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute Products product) {
        service.edit(product);
        return "redirect:/admin/product/edit/" + product.getProductId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "redirect:/admin/product";
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
}
