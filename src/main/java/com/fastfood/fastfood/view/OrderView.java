package com.fastfood.fastfood.view;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fastfood.fastfood.entity.Order;

@Named(value = "orderBean")
@ViewScoped
public class OrderView implements Serializable {

    private Order order;
    private long orderId;

    @Inject
    private KitchenView kitchenView;

    @PostConstruct
    public void init(){
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        orderId = requestParams.containsKey("order_id") ? Long.parseLong(requestParams.get("order_id")) : -1;
        order = kitchenView.getOrders().stream()
            .filter(o -> o.getId() == orderId)
            .findFirst().orElse(null);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
