package com.fastfood.fastfood.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fastfood.fastfood.dao.DishDAO;
import com.fastfood.fastfood.dao.OrderDAO;
import com.fastfood.fastfood.entity.Dish;
import com.fastfood.fastfood.entity.Order;
import com.fastfood.fastfood.service.MakeOrderService;
import com.fastfood.fastfood.service.PaymentService;
import org.primefaces.model.ResponsiveOption;

@Named(value = "menuBean")
@SessionScoped
public class MenuView implements Serializable {

    @Inject
    private OrderDAO orderDAO;
    @Inject
    private DishDAO dishDAO;
    @Inject
    private MakeOrderService makeOrderService;

    private List<ResponsiveOption> responsiveOptions;

    private double totalSum;
    private List<Dish> selectDishes;
    private List<Dish> dishes;
    private Dish dish;

    @PostConstruct
    public void init() {
        selectDishes = new ArrayList<>();
        dishes = new ArrayList<>();
        dishes.addAll(dishDAO.getAllDishes());

        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("560px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("560px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
    }

    public void payOrder(){
        Order order = new Order();
        order.setDishes(selectDishes);
        order.setTotalSum(totalSum);

        orderDAO.saveOrderDishes(order);

        makeOrderService.payOrder(order.getId(), totalSum);

        selectDishes.clear();
        totalSum = 0;
    }

    public void addDishToProductBucket(int dishId){
        Dish dish = dishes
            .stream()
            .filter(d -> d.getId() == dishId)
            .findFirst().orElse(null);
        selectDishes.add(dish);
        totalSum = totalSum + dish.getPrize();
    }

    public void removeDishFromOrder(int dishId){
        Dish dish = dishes
            .stream()
            .filter(d -> d.getId() == dishId)
            .findFirst().orElse(null);
        selectDishes.remove(dish);
        totalSum = totalSum - dish.getPrize();
    }
    public List<Dish> getSelectDishes() {
        return selectDishes;
    }

    public void setSelectDishes(List<Dish> selectDishes) {
        this.selectDishes = selectDishes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }
}
