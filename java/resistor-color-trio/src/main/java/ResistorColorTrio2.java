public class ResistorColorTrio {

    // Mapping of color to its corresponding value
    private static final String[] COLORS = {
        "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"
    };

    // Method to get the index (value) of a color
    private int colorValue(String color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equalsIgnoreCase(color)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid color: " + color);
    }

    // Method to generate the label for the given colors
    public String label(String[] colors) {
        // Ignore extra colors and use only the first three
        if (colors.length < 3) {
            throw new IllegalArgumentException("Exactly three colors are required");
        }

        // Use only the first three colors
        int firstDigit = colorValue(colors[0]);
        int secondDigit = colorValue(colors[1]);
        int multiplier = colorValue(colors[2]);

        // Calculate the resistance value in ohms
        long resistanceValue = (long) ((firstDigit * 10 + secondDigit) * Math.pow(10, multiplier));

        // Determine the label with appropriate unit
        String label;
        if (resistanceValue >= 1_000_000_000) {
            label = (resistanceValue / 1_000_000_000) + " gigaohms";
        } else if (resistanceValue >= 1_000_000) {
            label = (resistanceValue / 1_000_000) + " megaohms";
        } else if (resistanceValue >= 1_000) {
            label = (resistanceValue / 1_000) + " kiloohms";
        } else {
            label = resistanceValue + " ohms";
        }

        return label;
    }

    public static void main(String[] args) {
        ResistorColorTrio resistor = new ResistorColorTrio();

        // Test cases
        String[][] testCases = {
            {"orange", "orange", "black"},
            {"orange", "orange", "red"},
            {"orange", "orange", "orange"},
            {"yellow", "violet", "red"},
            {"blue", "grey", "brown"},
            {"white", "black", "white"},
            {"green", "blue", "yellow"},
            {"white", "white", "white"},
            {"blue", "green", "yellow", "orange"}
        };

        for (String[] testCase : testCases) {
            System.out.println(String.join(", ", testCase) + ": " + resistor.label(testCase));
        }
    }
}

