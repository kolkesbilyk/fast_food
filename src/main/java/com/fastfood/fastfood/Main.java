package com.fastfood.fastfood;

import java.io.IOException;

import com.fastfood.fastfood.service.ImageServiceImpl;

public class Main {

    public static void main(String[] args) throws IOException {
        ImageServiceImpl.correctSizeImage("C:\\Users\\Bilyk Mykola\\Downloads\\menu.png", "C:\\Users\\Bilyk Mykola\\IdeaProjects\\FastFood\\src\\main\\webapp\\resources\\images\\menu.png", 50, 50);
    }

}
