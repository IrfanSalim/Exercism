class Queen {
    public final int r, c;

    Queen(int row, int column) {
        if (row < 0) {
            throw new IllegalArgumentException("Queen position must have positive row.");
        } else if (row >= 8) {
            throw new IllegalArgumentException("Queen position must have row <= 7.");
        } else if (column < 0) {
            throw new IllegalArgumentException("Queen position must have positive column.");
        } else if (column >= 8) {
            throw new IllegalArgumentException("Queen position must have column <= 7.");
        }
        r = row;
        c = column;
    }
}