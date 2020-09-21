package org.zsy.libs.lg;

public interface Printer {

    boolean isPrefix();

    void println(int level, String baseTag, String tag, String msg);
}
