package org.zsy.libs.lg;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LogFormat implements ILogFormat {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("MM-dd HH:mm:ss sss", Locale.CHINA);
    private static final char TOP_LEFT_CORNER = '┌';
    private static final char BOTTOM_LEFT_CORNER = '└';
    private static final char MIDDLE_CORNER = '├';
    private static final char V_LINE = '│';
    private static final String TAB = " ";
    private static final String DOUBLE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final String SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String MSG_PREFIX = V_LINE + TAB;
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;


    private int minLevel;
    private boolean showDesInfo;
    private boolean showMethodTrace;
    private int methodOffset;
    private int methodCount;
    private int maxLineLen;
    private String baseTag;
    private Printer printer;


    private LogFormat(Builder builder) {
        this.minLevel = builder.minLevel;
        this.showDesInfo = builder.showDesInfo;
        this.showMethodTrace = builder.showMethodTrace;
        this.methodOffset = builder.methodOffset;
        this.methodCount = builder.methodCount;
        this.maxLineLen = builder.maxLineLen;
        this.baseTag = builder.baseTag;
        this.printer = builder.printer;
    }


    private String timeInfo() {
        return SDF.format(System.currentTimeMillis());
    }


    private String threadInfo() {
        Thread t = Thread.currentThread();
        return t.getName() + "(" + t.getId() + ")";
    }

    private String prefix(String tag) {
        String tagInfo = tag == null ? baseTag : baseTag + "-" + tag;
        return timeInfo() + " /" + tagInfo + " : ";
    }

    @Override
    public synchronized void log(int level, String tag, String msg) {
        if (!checkLevel(level)) {
            return;
        }
        String prefix = printer.isPrefix() ? prefix(tag) : "";
        printer.println(level, baseTag, tag, prefix + TOP_BORDER);
        if (showDesInfo) {
            printer.println(level, baseTag, tag, prefix + MSG_PREFIX + threadInfo());
            printer.println(level, baseTag, tag, prefix + MIDDLE_BORDER);
        }

        if (showMethodTrace) {
//            StackTraceElement[] traces = new Throwable().getStackTrace();
            StackTraceElement[] traces = Thread.currentThread().getStackTrace();
            int end = Math.min(methodOffset + methodCount, traces.length);
            for (int i = methodOffset; i < end; i++) {
                printer.println(level, baseTag, tag, prefix + MSG_PREFIX + "\tat " + traces[i]);
            }
            printer.println(level, baseTag, tag, prefix + MIDDLE_BORDER);
        }

//        StringBuilder sb = new StringBuilder(msg);
        int lineIndex = msg.length() / maxLineLen;
        for (int i = 0; i <= lineIndex; i++) {
            int start = i * maxLineLen;
            int end = i == lineIndex ? msg.length() : start + maxLineLen;
            printer.println(level, baseTag, tag, prefix + MSG_PREFIX + msg.subSequence(start, end));
        }

        printer.println(level, baseTag, tag, prefix + BOTTOM_BORDER);

    }


    private boolean checkLevel(int level) {
        return level >= minLevel;
    }

    public static class Builder {
        private int minLevel = Lg.VERBOSE;
        private boolean showDesInfo = true;
        private boolean showMethodTrace = true;
        private int methodOffset = 3;
        private int methodCount = 3;
        private int maxLineLen = 2000;
        private String baseTag = "Lg";
        private Printer printer = SystemPrinter.INSTACNE;


        public Builder setMinLevel(int minLevel) {
            this.minLevel = minLevel;
            return this;
        }

        public Builder setShowDesInfo(boolean showDesInfo) {
            this.showDesInfo = showDesInfo;
            return this;
        }

        public Builder setShowMethodTrace(boolean showMethodTrace) {
            this.showMethodTrace = showMethodTrace;
            return this;
        }

        public Builder setMethodOffset(int methodOffset) {
            this.methodOffset = methodOffset;
            return this;
        }

        public Builder setMethodCount(int methodCount) {
            this.methodCount = methodCount;
            return this;
        }

        public Builder setMaxLineLen(int maxLineLen) {
            this.maxLineLen = maxLineLen;
            return this;
        }

        public Builder setBaseTag(String baseTag) {
            this.baseTag = baseTag;
            return this;
        }

        public Builder setPrinter(Printer printer) {
            this.printer = printer;
            return this;
        }

        public LogFormat build() {
            return new LogFormat(this);
        }
    }
}
