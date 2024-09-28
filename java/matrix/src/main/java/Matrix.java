class Matrix {
    int[][] matrix;

    Matrix(String matrixAsString) {
        String[] input = matrixAsString.split("\n");
        int maxColumns = 0;

        // Determine the maximum number of columns
        for (String row : input) {
            String[] values = row.trim().split(" ");
            if (values.length > maxColumns) {
                maxColumns = values.length;
            }
        }

        matrix = new int[input.length][maxColumns];

        // Populate the matrix
        for (int i = 0; i < input.length; i++) {
            String[] values = input[i].trim().split(" ");
            for (int j = 0; j < values.length; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }
    }

    int[] getRow(int rowNumber) {
        rowNumber--;
        if (rowNumber < 0 || rowNumber >= matrix.length) {
            throw new IndexOutOfBoundsException("Row number out of bounds");
        }
        return matrix[rowNumber];
    }

    int[] getColumn(int columnNumber) {
        columnNumber--;
        if (columnNumber < 0 || columnNumber >= matrix[0].length) {
            throw new IndexOutOfBoundsException("Column number out of bounds");
        }
        int[] column = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnNumber];
        }
        return column;
    }
}

import java.util.Arrays;

class Matrix {
    private int[][] matrix;

    Matrix(String matrixAsString) {
        matrix = Arrays.stream(matrixAsString.split("\n"))
                .map(row -> Arrays.stream(row.split("\\s"))
                        .mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }

    int[] getRow(int rowNumber) {
        return matrix[rowNumber - 1];
    }

    int[] getColumn(int columnNumber) {
        return Arrays.stream(matrix).mapToInt(row -> row[columnNumber - 1]).toArray();
    }
}