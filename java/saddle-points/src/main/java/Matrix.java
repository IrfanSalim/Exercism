import java.util.*;

class Matrix {

    final Set<MatrixCoordinate> saddlePoints = new HashSet<>();

    Matrix(List<List<Integer>> intLists) {
        for (int i = 0; i < intLists.size(); i++) {
            var row = intLists.get(i);
            List<Integer> maxIndexes = new ArrayList<>();
            var rowMax = Collections.max(row);

            for (int rowIndex = 0; rowIndex < row.size(); rowIndex++) {
                var rowValue = row.get(rowIndex);
                if (rowValue.equals(rowMax)) maxIndexes.add(rowIndex);
            }

            for (var rowIndex : maxIndexes) {
                List<Integer> column = new ArrayList<>();
                for (var rowX : intLists) column.add(rowX.get(rowIndex));
                Integer colMin = Collections.min(column);
                if (row.get(rowIndex).equals(colMin)) saddlePoints.add(new MatrixCoordinate(i + 1, rowIndex + 1));
            }
        }
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        return saddlePoints;
    }
}

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Matrix {

    private final Set<MatrixCoordinate> saddlePoints = new HashSet<>();

    Matrix(List<List<Integer>> matrix) {
        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.get(row).size(); col++) {

                var point = matrix.get(row).get(col);
                if (maxInRow(matrix.get(row)) == point && minInCol(matrix, col) == point)
                    saddlePoints.add(new MatrixCoordinate(row + 1, col + 1));
            }
        }
    }

    private int maxInRow(List<Integer> row) {
        return Collections.max(row);
    }

    private int minInCol(List<List<Integer>> matrix, int column) {
        return matrix.stream().map(r -> r.get(column)).min(Integer::compareTo).orElseThrow();
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        return new HashSet<>(saddlePoints);
    }
}

import java.util.*;
import java.util.stream.IntStream;

class Matrix {
    List<List<Integer>> values;
    Integer rows;
    Integer cols;

    Matrix(List<List<Integer>> values) {
        this.values = values;
        rows = values.size();
        cols = rows > 0 ? values.get(0).size() : 0;
    }

    List<Integer> getCol(Integer c) {
        return values.stream().map(r -> r.get(c)).toList();
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        var rowMax = IntStream.range(0, rows)
            .map(r -> Collections.max(values.get(r)))
            .toArray();
        var colMin = IntStream.range(0, cols)
            .map(c -> Collections.min(getCol(c)))
            .toArray();

        Set<MatrixCoordinate> result = new HashSet<MatrixCoordinate>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rowMax[r] == colMin[c])
                    result.add(new MatrixCoordinate(r+1, c+1));
            }
        }
        return result;
    }
}