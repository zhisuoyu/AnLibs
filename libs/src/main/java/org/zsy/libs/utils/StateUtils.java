package org.zsy.libs.utils;

public class StateUtils {

    public static final int STATE_FALSE = 0;
    public static final int STATE_TRUE = ~STATE_FALSE;


    public static void main(String[] args) {
        int state = setFalse(STATE_TRUE, 5);
        state = setFalse(state, 6);
        state = setFalse(state, 8);
        state = setFalse(state, 12);
        state = setTrue(state, 5);
        state = setTrue(state, 32);
        for (int i = 0; i < 31; i++) {
            System.out.println(i + " : " + isTrue(state, i));
        }
    }


    /**
     * @param state
     * @param position position>=0 && position<=31
     * @return
     */
    public static boolean isTrue(int state, int position) {
        if (position < 0 || position > 31) {
            throw new IllegalArgumentException("position is out of boundary:" + position);
        }
        return ((1 << position) & state) != 0;
    }

    public static int setTrue(int state, int position) {
        checkPosition(position);
        return (1 << position) | state;
    }

    public static int setFalse(int state, int position) {
        checkPosition(position);

        return state & (~(1 << position));
//        return state - (state & (1 << position));

    }

    private static void checkPosition(int position) {
        if (position < 0 || position > 31) {
            throw new IllegalArgumentException("position is out of boundary:" + position);
        }
    }

}
