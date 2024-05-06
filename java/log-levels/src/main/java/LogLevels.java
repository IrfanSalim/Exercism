public class LogLevels {
    
    public static String message(String logLine) {
      String[] arr = logLine.split(":");
      String newLogLine = arr[1].trim();
      return newLogLine;
    }

    public static String logLevel(String logLine) {
      String[] arr = logLine.split(":");
      String level = arr[0].substring(1, arr[0].length() - 1).trim().toLowerCase();
      return level;
    }

    public static String reformat(String logLine) {
      String message = message(logLine);
      String level = logLevel(logLine);
      return message + " (" + level + ")";
    }
}

import static java.lang.String.format;

public class LogLevels {

    public static String message(String logLine) {
        return logLine.split("]: ")[1]
                .trim();
    }

    public static String logLevel(String logLine) {
        return logLine.split("]: ")[0]
                .substring(1)
                .toLowerCase();
    }

    public static String reformat(String logLine) {
        return format("%s (%s)", message(logLine), logLevel(logLine));
    }
}

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLevels {

    private static final String REGEX = "\\[(?<level>[A-Z]+)\\]:\\s(?<message>.+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    
    public static String message(String logLine) {
        return match(logLine).group("message").strip();
    }

    public static String logLevel(String logLine) {
        return match(logLine).group("level").toLowerCase();
    }

    public static String reformat(String logLine) {
        return String.format("%s (%s)", 
            message(logLine), logLevel(logLine));
    }

    private static Matcher match(String logLine) {
        var matcher = PATTERN.matcher(logLine);
        matcher.find();
        return matcher;
    }
}
