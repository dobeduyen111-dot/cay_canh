package ceb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about")
    public String about() {
        return "pages/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "pages/contact";
    }

    @GetMapping("/privacy-policy")
    public String privacy() {
        return "pages/privacy-policy";
    }

    @GetMapping("/warranty-policy")
    public String warranty() {
        return "pages/warranty-policy";
    }

    @GetMapping("/payment-method")
    public String payment() {
        return "pages/payment-method";
    }

    @GetMapping("/shipping-method")
    public String shipping() {
        return "pages/shipping-method";
    }
    @GetMapping("/instruction")
    public String huongdan() {
        return "pages/instruction";
    }
}
