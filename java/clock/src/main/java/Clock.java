public class Clock {
    private static final int MINS_IN_HOUR = 60;
    private static final int MINS_IN_DAY = MINS_IN_HOUR * 24;

    private int mins = 0;

    public Clock(int h, int m) {
        this.add(h * MINS_IN_HOUR + m);
    }

    public void add(int m) {
        mins = (MINS_IN_DAY + (mins + m) % MINS_IN_DAY) % MINS_IN_DAY;
    }

    @Override
    public String toString() {
        return String.format("%1$02d:%2$02d", (int) (mins / MINS_IN_HOUR), mins % MINS_IN_HOUR);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Clock) && mins == ((Clock) obj).mins;
    }
}