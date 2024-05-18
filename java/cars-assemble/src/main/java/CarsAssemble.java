public class CarsAssemble {

    public double productionRatePerHour(int speed) {
      int success = successRate(speed);
      return (double) success / 100 * speed * 221.0;
    }

    public int workingItemsPerMinute(int speed) {
      return (int) productionRatePerHour(speed) / 60;
    }

    private int successRate(int speed) {
      if (speed > 0 && speed < 5) {
        return 100;
      } else if (speed >= 5 && speed < 9) {
        return 90;
      } else if (speed == 9) {
        return 80;
      } else if (speed == 10) {
        return 77;
      } else {
        return 0;
      }
    }
}
