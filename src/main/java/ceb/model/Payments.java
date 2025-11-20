package ceb.model;

import java.util.Date;

public class Payments {
    private Integer PaymentId;
    private Integer OrderId;
    private String PaymentMethod;
    private double Amount;
    private Date PaymentDate;
    private boolean IsSuccessful;

    public Payments() {}

    public Payments(Integer paymentId, Integer orderId, String paymentMethod,
                    double amount, Date paymentDate, boolean isSuccessful) {
        PaymentId = paymentId;
        OrderId = orderId;
        PaymentMethod = paymentMethod;
        Amount = amount;
        PaymentDate = paymentDate;
        IsSuccessful = isSuccessful;
    }

    public Integer getPaymentId() { return PaymentId; }
    public void setPaymentId(Integer paymentId) { PaymentId = paymentId; }

    public Integer getOrderId() { return OrderId; }
    public void setOrderId(Integer orderId) { OrderId = orderId; }

    public String getPaymentMethod() { return PaymentMethod; }
    public void setPaymentMethod(String paymentMethod) { PaymentMethod = paymentMethod; }

    public double getAmount() { return Amount; }
    public void setAmount(double amount) { Amount = amount; }

    public Date getPaymentDate() { return PaymentDate; }
    public void setPaymentDate(Date paymentDate) { PaymentDate = paymentDate; }

    public boolean isSuccessful() { return IsSuccessful; }
    public void setSuccessful(boolean successful) { IsSuccessful = successful; }
}
