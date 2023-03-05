package com.fastfood.fastfood.entity;

import java.util.Date;
import java.util.List;

public class Order {
    private long id;
    private List<Dish> dishes;
    private double totalSum;
    private Date created;
    private Date changed;
    private Date ready;
    private boolean inWork;
    private int worker;
    private String dopInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public Date getReady() {
        return ready;
    }

    public void setReady(Date ready) {
        this.ready = ready;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public String getDopInfo() {
        return dopInfo;
    }

    public void setDopInfo(String dopInfo) {
        this.dopInfo = dopInfo;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", dishes=" + dishes +
            ", totalSum=" + totalSum +
            ", created=" + created +
            ", changed=" + changed +
            ", ready=" + ready +
            ", inWork=" + inWork +
            ", worker=" + worker +
            ", dopInfo='" + dopInfo + '\'' +
            '}';
    }
}
