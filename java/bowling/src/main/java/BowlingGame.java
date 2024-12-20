class BowlingGame {
    private static int MAX_PINS = 10;
    private static int LAST_FRAME = 10;

    private int frames = 0;
    private int score = 0;
    private Integer pendingPins = null;
    private int countNext1 = 0;
    private int countNext2 = 0;

    void roll(int pins) {
        if (finished())
            throw new IllegalStateException("Cannot roll after game is over");
        if (pins < 0)
            throw new IllegalStateException("Negative roll is invalid");
        if ((pendingPins == null ? pins : pendingPins + pins) > MAX_PINS)
            throw new IllegalStateException("Pin count exceeds pins on the lane");
        if (countNext1 > 0) {
            score += pins * countNext1;
            countNext1 = countNext2;
            countNext2 = 0;
        }
        if (frames == LAST_FRAME) {
            if (pins != MAX_PINS)
                pendingPins = pins;
            return;
        }
        score += pins;
        if (pendingPins != null) {
            if (pendingPins + pins == MAX_PINS)
                countNext1++;
            pendingPins = null;
            frames++;
        } else if (pins == MAX_PINS) {
            countNext1++;
            countNext2++;
            frames++;
        } else {
            pendingPins = pins;
        }
    }

    int score() {
        if (!finished())
            throw new IllegalStateException("Score cannot be taken until the end of the game");
        return score;

    }

    private boolean finished() {
        return frames == LAST_FRAME && countNext1 == 0;
    }
}