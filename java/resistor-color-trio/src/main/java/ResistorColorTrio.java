import java.util.HashMap;

class ResistorColorTrio {
    // Method to initialize and return the color codes map
    private HashMap<String, String> getColorCodes() {
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

    private String getUnit(String zeroes) {
        switch (zeroes.length()) {
            case 9:
                return " gigaohms";
            case 8:
                return "00 megaohms";
            case 7:
                return "0 megaohms";
            case 6:
                return " megaohms";
            case 5:
                return "00 kiloohms";
            case 4:
                return "0 kiloohms";
            case 3:
                return " kiloohms";
            case 2:
                return "00 ohms";
            case 1:
                return "0 ohms";
            default:
                return " ohms";
        }
    }

    String label(String[] colors) {
        HashMap<String, String> colorCodes = getColorCodes();
        StringBuilder prefix = new StringBuilder();
        StringBuilder suffix = new StringBuilder();

        String color1 = colorCodes.get(colors[0]);
        String color2 = colorCodes.get(colors[1]);
        String color3 = colorCodes.get(colors[2]);

        if (!color1.equals("0")) prefix.append(color1);
        if (!color2.equals("0")) prefix.append(color2);
        else suffix.append(color2);

        for (int i = 0; i < Integer.parseInt(color3); i++) suffix.append("0");

        String unit = getUnit(suffix.toString());
        prefix.append(unit);

        return prefix.toString();
    }
}
