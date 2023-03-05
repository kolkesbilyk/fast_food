package com.fastfood.fastfood.entity;

import java.util.Date;
import java.util.List;

public class Menu {
    private int id;
    private String name;
    private String image;
    private Date created;
    private Date deactivated;
    private String description;
    private List<Dish> dishes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Date deactivated) {
        this.deactivated = deactivated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "\nMenu{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", image='" + image + '\'' +
            ", created=" + created +
            ", deactivated=" + deactivated +
            ", description='" + description + '\'' +
            ", dishes=" + dishes +
            '}';
    }
}
