package ceb.controller;

import ceb.model.Orders;
import ceb.model.Payments;
import ceb.service.OrderService;
import ceb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/{orderId}")
    public String showPayment(@PathVariable int orderId, Model model) {
        Orders order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        model.addAttribute("total", order.getTotalAmount());
        return "payment/index"; 
    }

    @PostMapping("/payment")
    public String processPayment(
            @RequestParam int orderId,
            @RequestParam String paymentMethod,
            @RequestParam double amount
    ) {
        Payments p = new Payments();
        p.setOrderId(orderId);
        p.setPaymentMethod(paymentMethod);
        p.setAmount(amount);
        p.setSuccessful(true); 
        int pid = paymentService.pay(p);

        return "redirect:/order/confirm/" + orderId;
    }

    @GetMapping("/order/confirm/{orderId}")
    public String confirm(@PathVariable int orderId, Model model) {
        Orders order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "order/confirm";
    }
}
