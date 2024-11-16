class QueenAttackCalculator {
    private final Queen q1, q2;

    QueenAttackCalculator(Queen queen1, Queen queen2) {
        if (queen1 == null || queen2 == null) {
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        } else if (queen1.r == queen2.r && queen1.c == queen2.c) {
            throw new IllegalArgumentException("Queens cannot occupy the same position.");
        }
        q1 = queen1;
        q2 = queen2;
    }

    boolean canQueensAttackOneAnother() {
        return (q1.r == q2.r || q1.c == q2.c || Math.abs(q1.r - q2.r) == Math.abs(q1.c - q2.c));
    }
}
