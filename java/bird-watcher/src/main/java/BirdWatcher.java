
class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
      return this.birdsPerDay;
    }

    public int getToday() {
      return birdsPerDay.length == 0 ? 0 : birdsPerDay[birdsPerDay.length -1];
    }

    public void incrementTodaysCount() {
      this.birdsPerDay[this.birdsPerDay.length - 1] += 1;
    }

    public boolean hasDayWithoutBirds() {
      for (int birds : this.birdsPerDay) {
        if (birds == 0) {
          return true;
        }
      }
      return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
      int count = 0;
      int length = Math.min(this.birdsPerDay.length, numberOfDays);
      for (int i = 0; i < length; i++) {
        count += this.birdsPerDay[i];
      }
      return count;
    }

    public int getBusyDays() {
      int count = 0;
      for (int i = 0; i < this.birdsPerDay.length; i++) {
        if (this.birdsPerDay[i] >= 5) {
          count += 1;
        }
      }
      return count;
    }
}


/*

    public boolean hasDayWithoutBirds() {
        return Arrays.stream(birdsPerDay).filter(a -> a == 0).findAny().isPresent();
    }
    public int getCountForFirstDays(int numberOfDays) {
       return Arrays.stream(birdsPerDay).limit(numberOfDays).sum();
    }
    public int getBusyDays() {
        return (int) Arrays.stream(birdsPerDay).filter(a -> a >= 5).count();
    }

*/
