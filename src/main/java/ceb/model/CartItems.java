package ceb.model;

import java.util.Date;

public class CartItems {
    private Integer CartItemId;
    private Integer CartId;
    private Integer ProductId;
    private int Quantity;
    private Date AddedAt;

    public CartItems() {}

    public CartItems(Integer cartItemId, Integer cartId, Integer productId,
                     int quantity, Date addedAt) {
        CartItemId = cartItemId;
        CartId = cartId;
        ProductId = productId;
        Quantity = quantity;
        AddedAt = addedAt;
    }

    public Integer getCartItemId() { return CartItemId; }
    public void setCartItemId(Integer cartItemId) { CartItemId = cartItemId; }

    public Integer getCartId() { return CartId; }
    public void setCartId(Integer cartId) { CartId = cartId; }

    public Integer getProductId() { return ProductId; }
    public void setProductId(Integer productId) { ProductId = productId; }

    public int getQuantity() { return Quantity; }
    public void setQuantity(int quantity) { Quantity = quantity; }

    public Date getAddedAt() { return AddedAt; }
    public void setAddedAt(Date addedAt) { AddedAt = addedAt; }
}
