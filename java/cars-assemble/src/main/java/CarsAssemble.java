public class CarsAssemble {

    public double productionRatePerHour(int speed) {
      int success = successRate(speed);
      return success / 100 * speed * 221;
    }

    public int workingItemsPerMinute(int speed) {
        throw new UnsupportedOperationException("Please implement the CarsAssemble.workingItemsPerMinute() method");
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
