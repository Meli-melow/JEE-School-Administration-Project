package classes;

/**
 * This enumeration defines valid duration for classes.
 * Used only in the views to limit choices when creating a course.
 */
public enum Duration {
    ONE_HOUR_HALF("1h30"),
    THREE_HOURS("3h00");

    private String durationValue;

    Duration(String hourValue) { this.durationValue = hourValue; }

    public String getDurationValue() { return durationValue; }

    public void setDurationValue(String newDurationValue) { durationValue = newDurationValue; }

    @Override
    public String toString() { return durationValue; }
}
