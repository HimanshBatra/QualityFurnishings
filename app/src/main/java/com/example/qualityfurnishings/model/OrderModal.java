package com.example.qualityfurnishings.model;

import java.util.List;

public class OrderModal {
    public OrderModal(int orderId, String orderStatus, String paymentMethod, List<Cart> cartList) {
        OrderId = orderId;
        OrderStatus = orderStatus;
        PaymentMethod = paymentMethod;
        this.cartList = cartList;
    }
    public OrderModal(){

    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    int OrderId;
    String OrderStatus;
    String PaymentMethod;
    private List<Cart> cartList;


}
