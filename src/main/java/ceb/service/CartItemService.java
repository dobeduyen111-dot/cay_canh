package ceb.service;

import ceb.model.Cart;
import ceb.model.CartItem;
import ceb.repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemsRepository itemRepo;

    @Autowired
    private CartService cartService;

    public void addItem(int userId, int productId, int quantity) {
        Cart cart = cartService.getOrCreate(userId);
        int cartId = cart.getCartId();

        CartItem item = itemRepo.findItem(cartId, productId);

        if (item == null) {
            itemRepo.addItem(cartId, productId, quantity); 
        } else {
            itemRepo.updateQuantity(item.getCartItemId(), item.getQuantity() + quantity);
        }
    }

    public List<CartItem> getItems(int userId) {
        Cart cart = cartService.getOrCreate(userId);
        return itemRepo.findAll(cart.getCartId());
    }


    public void updateQuantity(int cartItemId, int quantity) {
        itemRepo.updateQuantity(cartItemId, quantity);
    }

    public void remove(int cartItemId) {
        itemRepo.delete(cartItemId);
    }

    public void clear(int userId) {
        Cart cart = cartService.getOrCreate(userId);
        itemRepo.clear(cart.getCartId());
    }

    public long getTotal(int userId) {
        Cart cart = cartService.getOrCreate(userId);
        List<CartItem> items = itemRepo.findAll(cart.getCartId());

        long total = 0;
        for (CartItem item : items) {
            total += (long) item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public int getTotalQuantity(int userId) {
        Cart cart = cartService.getOrCreate(userId);
        return itemRepo.findAll(cart.getCartId())
                       .stream()
                       .mapToInt(CartItem::getQuantity)
            
                       .sum();
    }
}
