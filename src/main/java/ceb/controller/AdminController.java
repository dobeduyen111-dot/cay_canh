package ceb.controller;

import ceb.model.Users;
import ceb.repository.DashboardRepository;
import ceb.repository.OrdersRepository;
import ceb.repository.UserRepository;
import ceb.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin") 
public class AdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private OrdersRepository orderRepo; 
    @Autowired
    private DashboardRepository dashboardRepo;

    // Đã sửa lại đường dẫn thành "/dashboard" (gộp với class thành "/admin/dashboard")
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        int orders = dashboardRepo.getTotalOrders();
        double revenue = dashboardRepo.getTotalRevenue();
        int customers = dashboardRepo.getTotalCustomers();
        
        System.out.println("Orders: " + orders);
        System.out.println("Customers: " + customers);
        
        model.addAttribute("totalOrders", orders);
        model.addAttribute("totalRevenue", revenue);
        model.addAttribute("totalCustomers", customers);

        return "admin/dashboard"; 
    }

    // (TUI ĐÃ XÓA CÁI HÀM DASHBOARD() RỖNG Ở ĐÂY ĐỂ TRÁNH XUNG ĐỘT NHA)

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Users> list = userRepo.findAll();
        model.addAttribute("users", list);
        return "admin/users";
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders"; 
    }

    @PostMapping("/orders/update")
    public String updateStatus(@RequestParam int orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/orders";
    }
    
    @PostMapping("/users/update-password")
    public String updateUserPassword(@RequestParam("userId") int userId, 
                                     @RequestParam("password") String password) {
        userRepo.updatePassword(userId, password);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userRepo.deleteById(id);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderRepo.delete(id);
        return "redirect:/admin/orders";
    }
}