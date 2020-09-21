package org.zsy.libs.utils;

import java.lang.reflect.Array;
import java.util.Collection;

public class ArrayUtils {


    public static <T> T[] concat(Class<T> cls, T[]... src) throws NullPointerException {
        if (src.length == 1) {
            return src[0];
        }
        int totalLen = 0;
        for (T[] e : src) {
            if (e == null) {
                continue;
            }
            totalLen += e.length;
        }
        T[] des = (T[]) Array.newInstance(cls, totalLen);//(T[]) new Object[totalLen];
        int currentLen = 0;
        for (T[] object : src) {
            if (object == null) {
                continue;
            }
            System.arraycopy(object, 0, des, currentLen, object.length);
            currentLen += object.length;
        }
        return des;
    }

    public static <E> E[] toTypeArray(Collection<E> c, Class<E> cls) {
        if (c == null) {
            return null;
        }
//        E[] arr = (E[]) new Object[c.size()];
        E[] arr = (E[]) Array.newInstance(cls, c.size());
        c.toArray(arr);
        return arr;
    }


//    public static <T> T[] concat2(T[]... src) throws NullPointerException {
//        if (src.length == 1) {
//            return src[0];
//        }
//        int totalLen = 0;
//        for (T[] e : src) {
//            if (e == null) {
//                continue;
//            }
//            totalLen += e.length;
//        }
//        T[] des = (T[]) new Object[totalLen];
//        int currentLen = 0;
//        for (T[] object : src) {
//            if (object == null) {
//                continue;
//            }
//            System.arraycopy(object, 0, des, currentLen, object.length);
//            currentLen += object.length;
//        }
//        return des;
//    }


    public static <T> boolean contain(T[] array, T element) {
        for (T a : array) {
            if (element.equals(a)) {
                return true;
            }
        }
        return false;

    }


//    public static <E> E[] toTypeArray(Collection<E> c) {
//        if (c == null) {
//            return null;
//        }
//        E[] arr = (E[]) new Object[c.size()];
//        c.toArray(arr);
//        return arr;
//    }
}
