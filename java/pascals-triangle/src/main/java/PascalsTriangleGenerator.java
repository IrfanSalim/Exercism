// class PascalsTriangleGenerator {

//     int[][] generateTriangle(int rows) {
//         int[][] triangle = new int[rows][];
//         for (int r = 0; r < rows; r++) {
//             triangle[r] = new int[r + 1];
//             triangle[r][0] = 1;
//             triangle[r][r] = 1;
//             for (int e = 1; e < r; e++) {
//                 triangle[r][e] = triangle[r - 1][e - 1] + triangle[r - 1][e];
//             }
//         }
//         return triangle;
//     }
// }

import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.stream.Stream;

public class PascalsTriangleGenerator {
    public static int[][] generateTriangle(int size) {
        return Stream
                .iterate(new int[] { 1 }, PascalsTriangleGenerator::nextRow)
                .limit(size)
                .toArray(int[][]::new);
    }

    private static int[] nextRow(int[] row) {
        return IntStream
                .range(0, row.length + 1)
                .map(i -> i > 0 && i < row.length ? row[i] + row[i - 1] : 1)
                .toArray();
    }

}