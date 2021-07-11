package com.example.qualityfurnishings.model;

import java.util.List;

public class OrderModal {
    public OrderModal(String orderId, String orderStatus, String paymentMethod, List<Cart> cartList) {
        OrderId = orderId;
        OrderStatus = orderStatus;
        PaymentMethod = paymentMethod;
        this.cartList = cartList;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
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

    String  OrderId,OrderStatus,PaymentMethod;
    private List<Cart> cartList;


}
