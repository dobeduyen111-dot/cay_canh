package ceb.service;

import ceb.model.Products;
import ceb.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public void addIfNotExists(int userId, int productId) {
        wishlistRepository.add(userId, productId);
    }

    public List<Products> getWishlistByUser(int userId) {
        return wishlistRepository.getWishlist(userId);
    }
    public int countWishlistByUser(int userId) {
    return wishlistRepository.countWishlist(userId);
    }
    public void remove(int userId, int productId) {
        wishlistRepository.remove(userId, productId);
    }

}
