class GameOfLife {
    int[] x = {1, 0, -1, 0, 1, -1, 1, -1};
    int[] y = {0, 1, 0, -1, 1, -1, -1, 1};

    public int[][] tick(int[][] matrix) {
        if (matrix.length == 0) return new int[0][0];
        // Create a copy of the matrix to store the new state
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int countLive = count(matrix, i, j);
                if (matrix[i][j] == 0) {
                    if (countLive == 3) newMatrix[i][j] = 1;
                } else {
                    if (countLive != 2 && countLive != 3) newMatrix[i][j] = 0;
                    else newMatrix[i][j] = 1;
                }
            }
        }
        return newMatrix;
    }

    private int count(int[][] matrix, int i, int j) {
        int count = 0;
        for (int k = 0; k < 8; k++) {
            int newI = i + x[k];
            int newJ = j + y[k];
            if (newI >= 0 && newJ >= 0 && newI < matrix.length && newJ < matrix[0].length && matrix[newI][newJ] == 1) {
                count++;
            }
        }
        return count;
    }
}
