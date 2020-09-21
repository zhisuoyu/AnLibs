package org.zsy.libs.utils;

import java.util.HashMap;

/**
 * Created by 24275 on 2016/8/24.
 */
public class TimeFilter {

    public static final String EVENT_TAG = "EVENT_TAG";

    private static final HashMap<String, Long> Maps = new HashMap<>();

    public static boolean isPast(String tag, long periodMs) {
        long currentTime = System.currentTimeMillis();
        Long preMs = Maps.get(tag);
        if (preMs == null || currentTime - periodMs > periodMs) {
            Maps.put(tag, currentTime);
            return false;
        }
        return true;
    }


    public static boolean isEventPast(long periodMs) {
        return isPast(EVENT_TAG, periodMs);
    }

    public static void clear(String tag) {
        Maps.remove(tag);
    }

    public static void clearEvent() {
        clear(EVENT_TAG);
    }


    public static void clearAll() {
        Maps.clear();
    }

}
