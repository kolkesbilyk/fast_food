package com.fastfood.fastfood;

import java.io.IOException;
import java.util.Date;

import com.fastfood.fastfood.utils.Util;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Date().getTime());
        //https://testhorareact.finmdtest.com/?utm_source=test_sourse&fcm_token=test_token&fcm_token_date=1689674109739
    }

    public static void image() throws IOException {
        Util.correctSizeImage("C:\\Users\\Bilyk Mykola\\Downloads\\menu.png", "C:\\Users\\Bilyk Mykola\\IdeaProjects\\FastFood\\src\\main\\webapp\\resources\\images\\menu.png", 50, 50);
    }

}
