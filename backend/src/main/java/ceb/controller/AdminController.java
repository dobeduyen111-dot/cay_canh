package ceb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Users;
import ceb.repository.DashboardRepository;
import ceb.repository.OrdersRepository;
import ceb.repository.UserRepository;
import ceb.service.OrderService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrdersRepository orderRepo;

    @Autowired
    private DashboardRepository dashboardRepo;

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalOrders", dashboardRepo.getTotalOrders());
        data.put("totalRevenue", dashboardRepo.getTotalRevenue());
        data.put("totalCustomers", dashboardRepo.getTotalCustomers());
        return data;
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/orders")
    public List<?> getOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/orders/{id}/status")
    public String updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return "Update thành công";
    }

    @PutMapping("/users/{id}/password")
    public String updateUserPassword(@PathVariable int id,
                                     @RequestParam String password) {
        userRepo.updatePassword(id, password);
        return "Update password thành công";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepo.deleteById(id);
        return "Xóa user thành công";
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderRepo.delete(id);
        return "Xóa order thành công";
    }
}