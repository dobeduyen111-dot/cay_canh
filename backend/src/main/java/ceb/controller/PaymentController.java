package ceb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Orders;
import ceb.model.Payments;
import ceb.service.OrderService;
import ceb.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    // ✅ Lấy thông tin thanh toán
    @GetMapping("/{orderId}")
    public Map<String, Object> getPaymentInfo(@PathVariable int orderId) {

        Orders order = orderService.getOrder(orderId);

        if (order == null) {
            throw new RuntimeException("Không tìm thấy đơn hàng");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("order", order);
        data.put("total", order.getTotalAmount());

        return data;
    }

    // ✅ Xử lý thanh toán
    @PostMapping
    public Map<String, Object> processPayment(@RequestBody Map<String, Object> request) {

        int orderId = (int) request.get("orderId");
        String paymentMethod = (String) request.get("paymentMethod");
        double amount = Double.parseDouble(request.get("amount").toString());

        Payments p = new Payments();
        p.setOrderId(orderId);
        p.setPaymentMethod(paymentMethod);
        p.setAmount(amount);
        p.setSuccessful(true);

        int paymentId = paymentService.pay(p);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Thanh toán thành công");
        response.put("orderId", orderId);
        response.put("paymentId", paymentId);

        return response;
    }

    // ✅ Xác nhận đơn hàng
    @GetMapping("/confirm/{orderId}")
    public Orders confirmOrder(@PathVariable int orderId) {

        Orders order = orderService.getOrder(orderId);

        if (order == null) {
            throw new RuntimeException("Không tìm thấy đơn hàng");
        }

        return order;
    }
}