package com.fastfood.fastfood.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.fastfood.fastfood.utils.Util;

public class AppProperties {

    private static Properties properties;

    static {
        System.out.println("INIT PROPERTIES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(new File("").getAbsolutePath());
        try{
            InputStream stream = Util.getResourcesAsStream("application.properties");
            properties = new Properties();
            properties.load(new InputStreamReader(stream));
        } catch (IOException e){
            System.err.println("application.properties not found, " + e);
        }
    }

    public static String getProperty(String propertyName){
        return properties.getProperty(propertyName);
    }
}
