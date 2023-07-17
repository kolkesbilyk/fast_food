package com.fastfood.fastfood.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfigServiceImpl implements ConfigService {

    private Properties properties;

    public ConfigServiceImpl() {
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init(){
        initProperties();
    }

    @Override
    public void initProperties(){
        System.out.println("INIT PROPERTIES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            File file = new File("src\\main\\resources\\application.properties");
            properties = new Properties();
            properties.load(new FileReader(file));
        } catch (IOException e){
            System.err.println("application.properties not found, " + e);
        }
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
