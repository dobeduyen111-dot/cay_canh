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

import ceb.repository.DashboardRepository;
import ceb.repository.OrdersRepository;
import ceb.repository.UsersRepository;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    
    @Autowired
    private UsersRepository userRepo;

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