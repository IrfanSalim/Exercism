class SpiralMatrixBuilder {
    int[][] buildMatrixOfSize(int size) {
      int[][] matrix = new int[size][size];
      int counter = 1;

      for (int i = 0; i < size; i++) {
        for (int j = i; j < size - i; j++) {
          matrix[i][j] = counter++;
        }
        for (int j = i + 1; j < size - i; j++) {
          matrix[j][size - i - 1] = counter++;
        }
        for (int j = size - i - 2; j >= i; j--) {
          matrix[size - i - 1][j] = counter++;
        }
        for (int j = size - i - 2; j > i; j--) {
          matrix[j][i] = counter++;
        }
      }
      
      return matrix;
    }
}
