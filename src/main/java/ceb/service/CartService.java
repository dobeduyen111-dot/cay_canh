package ceb.service;

import ceb.model.Cart;
import ceb.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    public Cart get(int userId) {
        return cartRepo.getByUserId(userId);
    }

    public Integer getCartIdIfExists(int userId) {
        Cart cart = cartRepo.getByUserId(userId);
        return cart != null ? cart.getCartId() : null;
    }

    public Cart getOrCreate(int userId) {
        Cart cart = cartRepo.getByUserId(userId);

        if (cart == null) {
            cartRepo.createCart(userId);
            cart = cartRepo.getByUserId(userId);
        }

        return cart;
    }
}
