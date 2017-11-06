package com.vinsofts.asus.apphero.helper;
import android.util.Log;

import java.util.IllegalFormatException;

/**
 * Handles logging formatted strings.
 */
public class LogUtils {
    public static String TAG = "LogUtils";

    /**
     * The minimum log level that will be printed to the console. Set this to
     * {@link Log#ERROR} for release or {@link Log#VERBOSE} for debugging.
     */
    public static int LOG_LEVEL = Log.ERROR;

    /**
     * Logs a formatted string to the console using the source object's name as
     * the log tag. If the source object is null, the default tag (see
     * {@link LogUtils#TAG} is used.
     * <p>
     * Example usage: <br>
     * <code>
     * LogUtils.log(this, Log.ERROR, "Invalid value: %d", value);
     * </code>
     *
     * @param source The object that generated the log event.
     * @param priority The log entry priority, see
     *            {@link Log#println(int, String, String)}.
     * @param format A format string, see
     *            {@link String#format(String, Object...)}.
     * @param args String formatter arguments.
     */
    public static void log(Object source, int priority, String format, Object... args) {
        if (priority < LOG_LEVEL) {
            return;
        }

        final String sourceClass;

        if (source == null) {
            sourceClass = TAG;
        } else if (source instanceof Class<?>) {
            sourceClass = ((Class<?>) source).getSimpleName();
        } else {
            sourceClass = source.getClass().getSimpleName();
        }

        try {
            Log.println(priority, sourceClass, String.format(format, args));
        } catch (IllegalFormatException e) {
            Log.e(TAG, "Bad formatting string: \"" + format + "\"", e);
        }
    }

    /**
     * Logs a formatted string to the console using the default tag (see
     * {@link LogUtils#TAG}.
     *
     * @param priority The log entry priority, see
     *            {@link Log#println(int, String, String)}.
     * @param format A format string, see
     *            {@link String#format(String, Object...)}.
     * @param args String formatter arguments.
     */
    public static void log(int priority, String format, Object... args) {
        log(null, priority, format, args);
    }

    /**
     * Sets the log display level.
     *
     * @param logLevel The minimum log level that will be printed to the
     *            console.
     */
    public static void setLogLevel(int logLevel) {
        LOG_LEVEL = logLevel;
    }
}