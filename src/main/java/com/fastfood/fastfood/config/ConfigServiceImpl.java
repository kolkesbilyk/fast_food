package com.fastfood.fastfood.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.enterprise.context.ApplicationScoped;

import com.fastfood.fastfood.entity.ConnectionPool;
import com.fastfood.fastfood.utils.Util;
import org.json.JSONArray;
import org.json.JSONObject;

@ApplicationScoped
public class ConfigServiceImpl implements ConfigService {

    private final Map<String, Object> cache = new HashMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public <T> T getOrDefault(T defaultValue, String... keys){
        try {
            Object value = get(keys);
            if (value != null && defaultValue != null && !defaultValue.getClass().isInstance(value)) {
                throw new ClassCastException();
            }
            return value == null ? defaultValue : (T) value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return defaultValue;
        }
    }

    private Object get(String... keys) {
        String key = (keys.length == 1 ? keys[0] : Arrays.toString(keys));
        Object value = cache.get(key);
        if (value == null) {
            rwl.writeLock().lock();
            try {
                if (keys.length > 1) {
                    value = getJSONObjectValue(keys);
                    if (value instanceof JSONObject) {
                        value = Util.jsonToMap((JSONObject) value);
                    } else if (value instanceof JSONArray) {
                        value = Util.jsonToList((JSONArray) value);
                    }
                } else {
                    value = getDBStr(key);
                    if (value != null && value.toString().trim().startsWith("{")) {
                        value = Util.jsonToMap(new JSONObject((String) value));
                    } else if (value != null && value.toString().trim().startsWith("[")) {
                        value = Util.jsonToList(new JSONArray((String) value));
                    }
                }
                if (value != null) {
                    cache.put(key, value);
                }
            } finally {
                rwl.writeLock().unlock();
                if (value == null) {
                    System.out.println("--== Non-cached param " + key + ", value is null");
                } else {
                    System.out.println("--== Cached param " + key + " (" + value.getClass().getSimpleName() + "), value=" + value);
                }
            }
        }
        return value;
    }

    private Object getJSONObjectValue(String... keys) {
        JSONObject json = getJSONObjectParam(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            if (i == keys.length - 1) {
                return json == null || !json.has(keys[i]) ? null : json.get(keys[i]);
            }
            json = json == null || !json.has(keys[i]) ? null : json.getJSONObject(keys[i]);
        }
        return json;
    }

    private JSONObject getJSONObjectParam(String param_name) {
        String result = getDBParam(param_name);
        return result == null || result.isEmpty() ? null : new JSONObject(result);
    }

    private String getDBParam(String param_name) {
        try {
            return getDBStr(param_name);
        } catch (Exception ignored) {
        }
        return null;
    }

    private String getDBStr(String param_name) {
        try (Connection connection = ConnectionPool.getConn();
            PreparedStatement statement = connection.prepareStatement("SELECT value FROM back.config WHERE name=?")) {
            statement.setString(1, param_name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
