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
            File file = new File("application.properties");
            Properties properties = new Properties();
            properties.load(new FileReader(file));
            System.out.println("found " + properties);
        } catch (IOException e){
            System.err.println("application.properties not found, " + e);
        }
    }

    @Override
    public Properties getProperties() {
        System.out.println(properties);
        return properties;
    }
}
