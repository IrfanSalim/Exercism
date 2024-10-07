import java.util.ArrayList;
import java.util.List;

class MinesweeperBoard {

    private final List<String> boardRows;

    MinesweeperBoard(List<String> boardRows) {
        this.boardRows = new ArrayList<>(boardRows);
    }

    List<String> withNumbers() {
        int rows = boardRows.size();
        if (rows == 0)
            return new ArrayList<>();
        int cols = boardRows.get(0).length();
        List<StringBuilder> numberedBoard = new ArrayList<>();

        // Initialize the numbered board with the same dimensions
        for (int i = 0; i < rows; i++) {
            numberedBoard.add(new StringBuilder());
        }

        // Directions for the 8 adjacent cells
        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        // Process each cell in the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (boardRows.get(i).charAt(j) == '*') {
                    numberedBoard.get(i).append('*'); // Keep mine
                } else {
                    int mineCount = 0;
                    // Check all 8 directions
                    for (int[] direction : directions) {
                        int newRow = i + direction[0];
                        int newCol = j + direction[1];

                        // Check if the new coordinates are within bounds
                        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                            if (boardRows.get(newRow).charAt(newCol) == '*') {
                                mineCount++;
                            }
                        }
                    }
                    // Append space if there are no adjacent mines, else append mine count
                    if (mineCount == 0) {
                        numberedBoard.get(i).append(' '); // Use space for zero adjacent mines
                    } else {
                        numberedBoard.get(i).append(mineCount); // Add mine count
                    }
                }
            }
        }

        // Convert StringBuilder to String and collect results
        List<String> result = new ArrayList<>();
        for (StringBuilder row : numberedBoard) {
            result.add(row.toString());
        }

        return result;
    }
}

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MinesweeperBoard {
    private final char[][] m;

    MinesweeperBoard(List<String> map) {
        m = map.stream().map(String::toCharArray).collect(Collectors.toList()).toArray(new char[map.size()][]);
    }

    public List<String> withNumbers() {
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                if (m[i][j] == ' ')
                    m[i][j] = minesAround(i, j);
        return Arrays.asList(m).parallelStream().map(String::valueOf).collect(Collectors.toList());
    }

    private char minesAround(int i, int j) {
        char count = '0';
        for (int x = max(i - 1, 0); x <= min(i + 1, m.length - 1); x++)
            for (int y = max(j - 1, 0); y <= min(j + 1, m[i].length - 1); y++)
                if (m[x][y] == '*')
                    count++;
        if (count == '0')
            count = ' ';
        return count;
    }

}