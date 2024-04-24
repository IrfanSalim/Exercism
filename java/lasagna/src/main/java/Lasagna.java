public class Lasagna {
    public int expectedMinutesInOven() {
      return 40;
    }

    public int remainingMinutesInOven(int actualMinutesInOven) {
      int total = expectedMinutesInOven();
      return total - actualMinutesInOven;
    }

    public int preparationTimeInMinutes(int numberOfLayers) {
      return 2 * numberOfLayers;
    }

    public int totalTimeInMinutes(int numberOfLayers, int actualMinutesInOven) {
      int preparation = preparationTimeInMinutes(numberOfLayers);
      return preparation + actualMinutesInOven;
    }
}
