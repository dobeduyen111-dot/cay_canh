package ceb.model;

public class OrderItems {
    private Integer OrderItemId;
    private Integer OrderId;
    private Integer ProductId;
    private int Quantity;
    private double Price;

    public OrderItems() {}

    public OrderItems(Integer orderItemId, Integer orderId, Integer productId,
                      int quantity, double price) {
        OrderItemId = orderItemId;
        OrderId = orderId;
        ProductId = productId;
        Quantity = quantity;
        Price = price;
    }

    public Integer getOrderItemId() { return OrderItemId; }
    public void setOrderItemId(Integer orderItemId) { OrderItemId = orderItemId; }

    public Integer getOrderId() { return OrderId; }
    public void setOrderId(Integer orderId) { OrderId = orderId; }

    public Integer getProductId() { return ProductId; }
    public void setProductId(Integer productId) { ProductId = productId; }

    public int getQuantity() { return Quantity; }
    public void setQuantity(int quantity) { Quantity = quantity; }

    public double getPrice() { return Price; }
    public void setPrice(double price) { Price = price; }
}
