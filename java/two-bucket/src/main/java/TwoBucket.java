class TwoBucket {
    private class Bucket {
        final int bucketSize;
        final String name;
        int liters;

        public Bucket(int bucketSize, String name) {
            this.name = name;
            this.bucketSize = bucketSize;
            this.liters = 0;
        }
    }

    private int moves = 0;
    private int desired;
    private Bucket firstBucket, secondBucket;

    // constructor
    public TwoBucket(int oneSize, int twoSize, int desired, String startBucket) {
        this.desired = desired;
        if (startBucket.equals("one")) {
            firstBucket = new Bucket(oneSize, "one");
            secondBucket = new Bucket(twoSize, "two");
        } else {
            firstBucket = new Bucket(twoSize, "two");
            secondBucket = new Bucket(oneSize, "one");
        }
        calculateTwoBuckets();
    }

    private void calculateTwoBuckets() {
        if (secondBucket.bucketSize == desired) {
            fill();
            secondBucket.liters = secondBucket.bucketSize;
            moves += 2;
        }
        while (firstBucket.liters != desired && secondBucket.liters != desired) {
            if (firstBucket.liters == 0)
                fill();
            else if (secondBucket.liters == secondBucket.bucketSize)
                empty();
            else
                pour();
            moves++;
        } // end while loop
    }

    private void pour() {
        int canTake = secondBucket.bucketSize - secondBucket.liters;
        int pouringLiters = Math.min(firstBucket.liters, canTake);
        firstBucket.liters -= pouringLiters;
        secondBucket.liters += pouringLiters;
    }

    private void fill() {
        firstBucket.liters = firstBucket.bucketSize;
    }

    private void empty() {
        secondBucket.liters = 0;
    }

    public int getTotalMoves() {
        return moves;
    }

    public String getFinalBucket() {
        return firstBucket.liters == desired ? firstBucket.name : secondBucket.name;
    }

    public int getOtherBucket() {
        return firstBucket.liters == desired ? secondBucket.liters : firstBucket.liters;
    }
}