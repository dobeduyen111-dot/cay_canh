package ceb.model;

import java.util.Date;

public class Cart {
    private Integer CartId;
    private Integer UserId;
    private Date UpdatedAt;

    public Cart() {}

    public Cart(Integer cartId, Integer userId, Date updatedAt) {
        CartId = cartId;
        UserId = userId;
        UpdatedAt = updatedAt;
    }

    public Integer getCartId() { return CartId; }
    public void setCartId(Integer cartId) { CartId = cartId; }

    public Integer getUserId() { return UserId; }
    public void setUserId(Integer userId) { UserId = userId; }

    public Date getUpdatedAt() { return UpdatedAt; }
    public void setUpdatedAt(Date updatedAt) { UpdatedAt = updatedAt; }
}
