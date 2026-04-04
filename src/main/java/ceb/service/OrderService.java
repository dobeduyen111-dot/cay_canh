package ceb.service;

import ceb.model.Orders;
import ceb.model.OrderItems;
import ceb.model.CartItem;
import ceb.repository.OrdersRepository;
import ceb.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepo;

    @Autowired
    private OrderItemsRepository orderItemsRepo;

    @Autowired
    private CartItemService cartItemService; // từ phần cart bạn có

    @Autowired
    private OrdersRepository ordersRepository;


    @Transactional
    public int createOrderFromCart(int userId, String shippingAddress, String note) {
        // lấy cart items
        List<CartItem> cartItems = cartItemService.getItems(userId);
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalStateException("Giỏ hàng trống");
        }

        double total = 0;
        for (CartItem ci : cartItems) {
            total += ci.getProduct().getPrice() * ci.getQuantity();
        }

        Orders order = new Orders();
        order.setUserId(userId);
        order.setShippingAddress(shippingAddress);
        order.setNote(note);
        order.setTotalAmount(total);
        order.setStatus("Chờ xử lý");

        // create order
        int orderId = ordersRepo.createOrder(order);

        // insert order items
        for (CartItem ci : cartItems) {
            OrderItems oi = new OrderItems();
            oi.setOrderId(orderId);
            oi.setProductId(ci.getProduct().getProductId());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getPrice());
            orderItemsRepo.insertItem(oi);
        }

        // clear cart
        cartItemService.clear(userId);

        return orderId;
    }

    public Orders getOrder(int orderId) {
        Orders o = ordersRepo.findById(orderId);
        o.setItems(orderItemsRepo.findByOrderId(orderId));
        return o;
    }

    public List<Orders> findByUser(int userId) {
        return ordersRepo.findByUserId(userId);
    }
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // [THÊM MỚI] Cho Admin cập nhật trạng thái
    public void updateOrderStatus(int orderId, String status) {
        ordersRepository.updateStatus(orderId, status);
    }

    // [THÊM MỚI] Cho User xem lịch sử (dùng hàm có sẵn trong Repo)
    public List<Orders> getUserOrders(int userId) {
        return ordersRepository.findByUserId(userId);
    }
    // Trong class OrderService
    public List<Orders> getOrdersByUserId(int userId) {
        // Hàm findByUserId đã có sẵn trong OrdersRepository bạn gửi rồi nhé
        return ordersRepository.findByUserId(userId);
    }
}
