public class Connect {
    private String[] board;
    private int rows, cols;

    public Connect(String[] board) {
        this.board = new String[board.length];

        for (int i = 0; i < board.length; i++) {
            this.board[i] = board[i].replaceAll("\\s", "");
        }

        rows = this.board.length;
        cols = this.board[0].length();
    }

    /**
     * Computes the winner in game of hex
     * 
     * @return Winner.None if game is incomplete, else the winner
     */
    public Winner computeWinner() {
        boolean[][] oTracker = getSkeletonBoard();
        boolean[][] xTracker = getSkeletonBoard();

        for (int i = 0; i < cols; i++) {
            if (isWinner(0, i, oTracker, 'O'))
                return Winner.PLAYER_O;
            if (isWinner(i, 0, xTracker, 'X'))
                return Winner.PLAYER_X;
        }

        return Winner.NONE;
    }

    /**
     * Determines if the player is winner by travelling through adjacent paths
     * 
     * @param currentRow row position
     * @param currentCol column position of current cell
     * @param tracker    A 2-d array to track whether a cell has been visited
     * @param ch
     * @return Boolean representing if game can be won from current cell position
     */
    private boolean isWinner(int currentRow, int currentCol, boolean[][] tracker, char ch) {
        // check if cell position isn't out of bound
        if (currentRow < 0 || currentRow >= rows)
            return false;
        if (currentCol < 0 || currentCol >= cols)
            return false;

        if (board[currentRow].charAt(currentCol) != ch || tracker[currentRow][currentCol])
            return false; // already visited or different cell

        if (ch == 'O' && currentRow == rows - 1)
            return true;
        if (ch == 'X' && currentCol == cols - 1)
            return true;

        tracker[currentRow][currentCol] = true; // marks as visited

        for (int i = currentRow - 1; i <= currentRow + 1; i++) {
            for (int j = currentCol - 1; j <= currentCol + 1; j++) {
                if (i - currentRow == j - currentCol)
                    continue; // skip unreachable cells
                if (isWinner(i, j, tracker, ch))
                    return true;
            }
        }

        return false;
    }

    /**
     * Returns a false array of same size as board
     * 
     * @return 2-d boolean array all with false values
     */
    private boolean[][] getSkeletonBoard() {
        return new boolean[rows][cols];
    }

}
