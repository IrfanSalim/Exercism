import java.util.HashMap;

class ResistorColorDuo {
    // Method to initialize and return the color codes map
    public static HashMap<String, String> getColorCodes() {
        HashMap<String, String> colorCodes = new HashMap<>();
        colorCodes.put("black", "0");
        colorCodes.put("brown", "1");
        colorCodes.put("red", "2");
        colorCodes.put("orange", "3");
        colorCodes.put("yellow", "4");
        colorCodes.put("green", "5");
        colorCodes.put("blue", "6");
        colorCodes.put("violet", "7");
        colorCodes.put("grey", "8");
        colorCodes.put("white", "9");
        return colorCodes;
    }

    int value(String[] colors) {
      HashMap<String, String> colorCodes = getColorCodes();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < Math.min(2, colors.length); i++) {
        sb.append(colorCodes.get(colors[i]));
      }
      return Integer.parseInt(sb.toString());
    }
}
