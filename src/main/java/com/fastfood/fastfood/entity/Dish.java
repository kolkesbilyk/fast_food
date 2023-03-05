package com.fastfood.fastfood.entity;

import java.util.Date;

public class Dish {
    private int id;
    private int menuId;
    private String name;
    private Date created;
    private Date deactivated;
    private String image;
    private double prize;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if (image == null){
            image = "images/menu.png";
        }
        this.image = image;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nDish{" +
            "id=" + id +
            ", menuId=" + menuId +
            ", name='" + name + '\'' +
            ", created=" + created +
            ", deactivated=" + deactivated +
            ", image='" + image + '\'' +
            ", prize=" + prize +
            ", description='" + description + '\'' +
            '}';
    }
}
