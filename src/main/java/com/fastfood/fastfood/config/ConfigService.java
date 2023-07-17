package com.fastfood.fastfood.config;

import java.io.Serializable;
import java.util.Properties;

public interface ConfigService extends Serializable {
    void initProperties();
    String getProperty(String key);
}
