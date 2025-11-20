package ceb.model;

import java.util.Date;

public class Orders {
    private Integer OrderId;
    private Integer UserId;
    private Date OrderDate;
    private String Status;
    private double TotalAmount;
    private String ShippingAddress;
    private String Note;

    public Orders() {}

    public Orders(Integer orderId, Integer userId, Date orderDate, String status,
                  double totalAmount, String shippingAddress, String note) {
        OrderId = orderId;
        UserId = userId;
        OrderDate = orderDate;
        Status = status;
        TotalAmount = totalAmount;
        ShippingAddress = shippingAddress;
        Note = note;
    }

    public Integer getOrderId() { return OrderId; }
    public void setOrderId(Integer orderId) { OrderId = orderId; }

    public Integer getUserId() { return UserId; }
    public void setUserId(Integer userId) { UserId = userId; }

    public Date getOrderDate() { return OrderDate; }
    public void setOrderDate(Date orderDate) { OrderDate = orderDate; }

    public String getStatus() { return Status; }
    public void setStatus(String status) { Status = status; }

    public double getTotalAmount() { return TotalAmount; }
    public void setTotalAmount(double totalAmount) { TotalAmount = totalAmount; }

    public String getShippingAddress() { return ShippingAddress; }
    public void setShippingAddress(String shippingAddress) { ShippingAddress = shippingAddress; }

    public String getNote() { return Note; }
    public void setNote(String note) { Note = note; }
}
