package org.zsy.libs.filter;

public class TimeFilter implements Filter {

    public static final long MS_DEFAULT_PERIOD = 1000;

    private long period = MS_DEFAULT_PERIOD;

    private long nextMs = -1;


    public TimeFilter() {
    }

    public TimeFilter(long period) {
        this.period = period;
    }

    @Override
    public boolean isVerified() {
        return isVerifiedAndNext(period);
    }

    public boolean isVerifiedAndNext(long period) {
        long currentMs = currentMs();
        if (currentMs >= nextMs) {
            nextMs = currentMs + period;
            return true;
        }
        return false;
    }

    protected long currentMs() {
        return System.currentTimeMillis();
    }

    public void next(long ms) {
        nextMs = currentMs() + ms;
    }

}
