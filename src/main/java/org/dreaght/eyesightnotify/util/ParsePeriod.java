package org.dreaght.eyesightnotify.util;

/**
 * Represents the period parsing util.
 */
public class ParsePeriod {
    /**
     * Converts the period to its milliseconds representation.
     * @param periodStr Period, examples: ("10s", "1m", "1h").
     * @return Milliseconds of period given.
     */
    public static long getPeriodFromString(String periodStr) {
        long period;
        char unit = periodStr.charAt(periodStr.length() - 1);

        switch (unit) {
            case 'h' -> period = getMillsFromHours(parseLong(periodStr));
            case 'm' -> period = getMillsFromMinutes(parseLong(periodStr));
            case 's' -> period = getMillsFromSeconds(parseLong(periodStr));
            default -> period = 0;
        }
        return period;
    }

    /**
     * Gets the parsed digits of period given.
     * @param periodStr String period.
     * @return Parsed digits.
     */
    public static long parseLong(String periodStr) {
        return Long.parseLong(periodStr.substring(0, periodStr.length() - 1));
    }

    public static long getMillsFromHours(long hours) {
        return hours * 60 * 60 * 1000;
    }

    public static long getMillsFromMinutes(long minutes) {
        return minutes * 60 * 1000;
    }

    public static long getMillsFromSeconds(long seconds) {
        return seconds * 1000;
    }
}
