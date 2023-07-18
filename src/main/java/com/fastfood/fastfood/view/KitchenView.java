package com.fastfood.fastfood.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fastfood.fastfood.dao.OrderDAO;
import com.fastfood.fastfood.entity.Order;

@Named(value = "kitchenBean")
@ViewScoped
public class KitchenView implements Serializable {

    @Inject
    private OrderDAO orderDAO;

    private long orderId;

    private List<Order> orders = new ArrayList<>();

    private Order selectedOrder;

    private Order orderInWork = new Order();

    public Order getOrderInWork() {
        System.out.println("find order");
        orderInWork = orders.stream()
            .filter(o -> o.getId() == orderId)
            .findFirst().orElse(null);
        return orderInWork;
    }

    public void setOrderInWork(Order orderInWork) {
        this.orderInWork = orderInWork;
    }

    @PostConstruct
    public void init(){
        orders = orderDAO.getOrders();

    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
