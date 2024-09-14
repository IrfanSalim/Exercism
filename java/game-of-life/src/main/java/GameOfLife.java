class GameOfLife {
    int[] x = {1, 0, -1, 0, 1, -1, 1, -1};
    int[] y = {0, 1, 0, -1, 1, -1, -1, 1};

    public int[][] tick(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int countLive = count(matrix, i, j);
                if (matrix[i][j] == 0) {
                    if (countLive == 3) matrix[i][j] = 1;
                } else {
                    if (countLive != 2 && countLive != 3) matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    private int count(int[][] matrix, int i, int j) {
        int count = 0;
        for (int k = 0; k < 8; k++) {
            int newI = i + x[k];
            int newJ = j + x[k];
            if (newI < matrix.length && newJ < matrix.length && newI >= 0 && newJ >= 0 && matrix[newI][newJ] == 1) count++;
        }
        return count;
    }
}
