package com.fastfood.fastfood.config;

import java.io.Serializable;
import java.util.Properties;

public interface ConfigService extends Serializable {

    <T> T getOrDefault(T defaultValue, String... keys);
}
