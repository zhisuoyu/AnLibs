package org.zsy.libs.framework.app;

import org.zsy.libs.lg.Lg;

import java.util.HashMap;
import java.util.Map;

public class MapActivity extends BaseActivity implements ActivityMap {

    private final Map<String, Object> AttrsMap = new HashMap<>();

    @Override
    public void put(String key, Object value) {
        AttrsMap.put(key, value);
    }

    @Override
    public Object get(String key) {
        return AttrsMap.get(key);
    }

    protected Object getAndRemove(String key) {
        Object value = get(key);
        Lg.i(TAG, "value:" + value);
        AttrsMap.remove(key);
        return value;
    }
}
