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
