// class BowlingGame {
//     private static int MAX_PINS = 10;
//     private static int LAST_FRAME = 10;

//     private int frames = 0;
//     private int score = 0;
//     private Integer pendingPins = null;
//     private int countNext1 = 0;
//     private int countNext2 = 0;

//     void roll(int pins) {
//         if (finished())
//             throw new IllegalStateException("Cannot roll after game is over");
//         if (pins < 0)
//             throw new IllegalStateException("Negative roll is invalid");
//         if ((pendingPins == null ? pins : pendingPins + pins) > MAX_PINS)
//             throw new IllegalStateException("Pin count exceeds pins on the lane");
//         if (countNext1 > 0) {
//             score += pins * countNext1;
//             countNext1 = countNext2;
//             countNext2 = 0;
//         }
//         if (frames == LAST_FRAME) {
//             if (pins != MAX_PINS)
//                 pendingPins = pins;
//             return;
//         }
//         score += pins;
//         if (pendingPins != null) {
//             if (pendingPins + pins == MAX_PINS)
//                 countNext1++;
//             pendingPins = null;
//             frames++;
//         } else if (pins == MAX_PINS) {
//             countNext1++;
//             countNext2++;
//             frames++;
//         } else {
//             pendingPins = pins;
//         }
//     }

//     int score() {
//         if (!finished())
//             throw new IllegalStateException("Score cannot be taken until the end of the game");
//         return score;

//     }

//     private boolean finished() {
//         return frames == LAST_FRAME && countNext1 == 0;
//     }
// }

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class BowlingGame {
    private final static int NUMBER_OF_FRAMES_PER_GAME = 10;
    private final static String ERROR_MESSAGE_ROLL_AFTER_GAME_OVER = "Cannot roll after game is over";
    private final static String ERROR_MESSAGE_SCORE_BEFORE_GAME_OVER = "Score cannot be taken until the end of the game";

    private List<Frame> frames = new CopyOnWriteArrayList<>();

    public BowlingGame() {
        frames.add(new Frame(this));
    }

    public void roll(int pins) {
        if (hasGameFinished()) {
            throw new IllegalStateException(ERROR_MESSAGE_ROLL_AFTER_GAME_OVER);
        }

        notifyFrames(pins);
    }

    public int score() {
        if (!hasGameFinished()) {
            throw new IllegalStateException(ERROR_MESSAGE_SCORE_BEFORE_GAME_OVER);
        }

        return frames
                .stream()
                .map(frame -> frame.getScore())
                .collect(Collectors
                        .summingInt(Integer::intValue));
    }

    public void nextFrame() {
        if (!areAllFramesCreated()) {
            frames.add(new Frame(this));
        }
    }

    private void notifyFrames(int pins) {
        for (Frame frame : frames) {
            frame.notifyRoll(pins);
        }
    }

    private boolean areAllFramesCreated() {
        return frames.size() == NUMBER_OF_FRAMES_PER_GAME;
    }

    private boolean hasGameFinished() {
        boolean openRolls = frames
                .stream()
                .filter(frame -> frame.hasOpenRolls())
                .count() > 0;

        return frames.size() == 10 &&
                !openRolls;
    }

    public class Frame {
        private static final String ERROR_MESSAGE_NEGATIVE_NUMBER_OF_PINS = "Negative roll is invalid";
        private static final String ERROR_MESSAGE_NUMBER_OF_PINS_EXCEEDED = "Pin count exceeds pins on the lane";

        private final BowlingGame game;
        private List<Integer> rolls = new ArrayList<>();
        private FrameType type = FrameType.OPEN_FRAME;

        public Frame(BowlingGame game) {
            this.game = game;
        }

        public void notifyRoll(int pins) {
            if (hasOpenRolls()) {
                handleRoll(pins);
            }
        }

        public boolean hasOpenRolls() {
            return (type == FrameType.STRIKE || type == FrameType.SPARE) && rolls.size() < 3 ||
                    type == FrameType.OPEN_FRAME && rolls.size() < 2;
        }

        public int getScore() {
            return rolls.stream()
                    .collect(Collectors
                            .summingInt(Integer::intValue));
        }

        private void handleRoll(int pins) {
            validateRoll(pins);

            if (isStrike(pins)) {
                type = FrameType.STRIKE;
                game.nextFrame();
            } else if (isSpare(pins)) {
                type = FrameType.SPARE;
                game.nextFrame();
            }

            rolls.add(pins);

            if (type == FrameType.OPEN_FRAME &&
                    rolls.size() == 2) {
                game.nextFrame();
            }
        }

        private void validateRoll(int pins) {
            checkIfRollIsNegative(pins);
            checkIfRollIsOver10(pins);
            checkIfTwoRollsInOpenFrameAreMoreThan10Pins(pins);
            checkIfTwoRegularRollsAfterStrikeDidNotExceed10Pins(pins);
        }

        private void checkIfRollIsNegative(int pins) {
            if (pins < 0) {
                throw new IllegalStateException(ERROR_MESSAGE_NEGATIVE_NUMBER_OF_PINS);
            }
        }

        private void checkIfRollIsOver10(int pins) {
            if (pins > 10) {
                throw new IllegalStateException(ERROR_MESSAGE_NUMBER_OF_PINS_EXCEEDED);
            }
        }

        private void checkIfTwoRollsInOpenFrameAreMoreThan10Pins(int pins) {
            if (type == FrameType.OPEN_FRAME &&
                    getScore() + pins > 10) {
                throw new IllegalStateException(ERROR_MESSAGE_NUMBER_OF_PINS_EXCEEDED);
            }
        }

        private void checkIfTwoRegularRollsAfterStrikeDidNotExceed10Pins(int pins) {
            if (type == FrameType.STRIKE &&
                    rolls.size() == 2 &&
                    rolls.get(1) < 10 &&
                    rolls.get(1) + pins > 10) {
                throw new IllegalStateException(ERROR_MESSAGE_NUMBER_OF_PINS_EXCEEDED);
            }
        }

        private boolean isStrike(int pins) {
            return type == FrameType.OPEN_FRAME &&
                    rolls.size() == 0 &&
                    pins == 10;
        }

        private boolean isSpare(int pins) {
            return type == FrameType.OPEN_FRAME &&
                    rolls.size() == 1 &&
                    rolls.get(0) + pins == 10;
        }
    }

    public enum FrameType {
        STRIKE,
        SPARE,
        OPEN_FRAME;
    }
}