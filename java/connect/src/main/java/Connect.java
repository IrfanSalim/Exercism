// public class Connect {
//     private String[] board;
//     private int rows, cols;

//     public Connect(String[] board) {
//         this.board = new String[board.length];

//         for (int i = 0; i < board.length; i++) {
//             this.board[i] = board[i].replaceAll("\\s", "");
//         }

//         rows = this.board.length;
//         cols = this.board[0].length();
//     }

//     /**
//      * Computes the winner in game of hex
//      * 
//      * @return Winner.None if game is incomplete, else the winner
//      */
//     public Winner computeWinner() {
//         boolean[][] oTracker = getSkeletonBoard();
//         boolean[][] xTracker = getSkeletonBoard();

//         for (int i = 0; i < cols; i++) {
//             if (isWinner(0, i, oTracker, 'O'))
//                 return Winner.PLAYER_O;
//             if (isWinner(i, 0, xTracker, 'X'))
//                 return Winner.PLAYER_X;
//         }

//         return Winner.NONE;
//     }

//     /**
//      * Determines if the player is winner by travelling through adjacent paths
//      * 
//      * @param currentRow row position
//      * @param currentCol column position of current cell
//      * @param tracker    A 2-d array to track whether a cell has been visited
//      * @param ch
//      * @return Boolean representing if game can be won from current cell position
//      */
//     private boolean isWinner(int currentRow, int currentCol, boolean[][] tracker, char ch) {
//         // check if cell position isn't out of bound
//         if (currentRow < 0 || currentRow >= rows)
//             return false;
//         if (currentCol < 0 || currentCol >= cols)
//             return false;

//         if (board[currentRow].charAt(currentCol) != ch || tracker[currentRow][currentCol])
//             return false; // already visited or different cell

//         if (ch == 'O' && currentRow == rows - 1)
//             return true;
//         if (ch == 'X' && currentCol == cols - 1)
//             return true;

//         tracker[currentRow][currentCol] = true; // marks as visited

//         for (int i = currentRow - 1; i <= currentRow + 1; i++) {
//             for (int j = currentCol - 1; j <= currentCol + 1; j++) {
//                 if (i - currentRow == j - currentCol)
//                     continue; // skip unreachable cells
//                 if (isWinner(i, j, tracker, ch))
//                     return true;
//             }
//         }

//         return false;
//     }

//     /**
//      * Returns a false array of same size as board
//      * 
//      * @return 2-d boolean array all with false values
//      */
//     private boolean[][] getSkeletonBoard() {
//         return new boolean[rows][cols];
//     }

// }

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Connect {
    static final int[] R_OFFSETS = { -1, -1, 0, 0, 1, 1 };
    static final int[] C_OFFSETS = { 0, 1, -1, 1, -1, 0 };

    String[] board;

    public Connect(String[] board) {
        this.board = Arrays.stream(board).map(line -> line.replace(" ", "")).toArray(String[]::new);
    }

    public Winner computeWinner() {
        int row = board.length;
        int col = board[0].length();

        for (int c = 0; c < col; ++c) {
            if (board[0].charAt(c) == 'O'
                    && findConnectedCells(0, c).stream().anyMatch(p -> p.r() == row - 1)) {
                return Winner.PLAYER_O;
            }
        }

        for (int r = 0; r < row; ++r) {
            if (board[r].charAt(0) == 'X'
                    && findConnectedCells(r, 0).stream().anyMatch(p -> p.c() == col - 1)) {
                return Winner.PLAYER_X;
            }
        }

        return Winner.NONE;
    }

    Set<Point> findConnectedCells(int r, int c) {
        Set<Point> connectedCells = new HashSet<>();
        search(connectedCells, r, c);

        return connectedCells;
    }

    void search(Set<Point> connectedCells, int r, int c) {
        int row = board.length;
        int col = board[0].length();

        connectedCells.add(new Point(r, c));

        for (int i = 0; i < R_OFFSETS.length; ++i) {
            int adjR = r + R_OFFSETS[i];
            int adjC = c + C_OFFSETS[i];
            if (adjR >= 0
                    && adjR < row
                    && adjC >= 0
                    && adjC < col
                    && board[adjR].charAt(adjC) == board[r].charAt(c)
                    && !connectedCells.contains(new Point(adjR, adjC))) {
                search(connectedCells, adjR, adjC);
            }
        }
    }
}

record Point(int r, int c) {
}
