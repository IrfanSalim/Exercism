// import java.util.ArrayList;

// class RectangleCounter {
//     String[] grid;

//     int countRectangles(String[] grid) {
//         this.grid = grid;
//         ArrayList<Integer> row = new ArrayList<Integer>();
//         ArrayList<Integer> col = new ArrayList<Integer>();
//         for (int i = 0; i < grid.length; i++)
//             for (int j = 0; j < grid[i].length(); j++)
//                 if (grid[i].charAt(j) == '+') {
//                     row.add(i);
//                     col.add(j);
//                 }
//         int result = 0;
//         for (int i = 0; i < row.size(); i++) {
//             int x0 = row.get(i);
//             int y0 = col.get(i);
//             for (int j = 0; j < row.size(); j++) {
//                 int x1 = row.get(j);
//                 int y1 = col.get(j);
//                 if (x0 < x1 && y0 < y1)
//                     if (checkRow(x0, y0, y1) && checkRow(x1, y0, y1) && checkCol(y0, x0, x1) && checkCol(y1, x0, x1))
//                         result++;
//             }
//         }
//         return result;
//     }

//     boolean checkRow(int x, int y0, int y1) {
//         for (int i = y0; i <= y1; i++)
//             if ("-+".indexOf(grid[x].charAt(i)) == -1)
//                 return false;
//         return true;
//     }

//     boolean checkCol(int y, int x0, int x1) {
//         for (int i = x0; i <= x1; i++)
//             if ("|+".indexOf(grid[i].charAt(y)) == -1)
//                 return false;
//         return true;
//     }

// }

import java.util.*;
import java.util.stream.*;

final class RectangleCounter {
    private static boolean hline(char[][] inputGrid, int[] a, int[] b) {
        return IntStream.range(a[0] + 1, b[0])
                .mapToObj(i -> inputGrid[a[1]][i])
                .allMatch(ch -> ch == '+' || ch == '-');
    }

    private static boolean vline(char[][] inputGrid, int[] a, int[] b) {
        return IntStream.range(a[1] + 1, b[1])
                .mapToObj(i -> inputGrid[i][a[0]])
                .allMatch(ch -> ch == '+' || ch == '|');
    }

    private static boolean validPartial(char[][] inputGrid, int[] a, int[] b) {
        return a[1] == b[1] && a[0] < b[0] && hline(inputGrid, a, b);
    }

    private static boolean validRectangle(char[][] inputGrid, int[] a, int[] b, int[] c) {
        return a[0] == c[0] && a[1] < c[1] &&
                inputGrid[c[1]][b[0]] == '+' &&
                vline(inputGrid, a, c) &&
                hline(inputGrid, c, b) &&
                vline(inputGrid, b, c);
    }

    public int countRectangles(String[] inputGrid) {
        return countRectangles(Arrays.stream(inputGrid)
                .map(s -> s.toCharArray())
                .toArray(char[][]::new));
    }

    public int countRectangles(char[][] inputGrid) {
        List<int[]> corners = IntStream.range(0, inputGrid.length)
                .mapToObj(y -> IntStream.range(0, inputGrid[y].length)
                        .filter(x -> inputGrid[y][x] == '+')
                        .mapToObj(x -> new int[] { x, y })
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return corners.stream()
                .mapToInt(a -> corners.stream()
                        .filter(b -> validPartial(inputGrid, a, b))
                        .mapToInt(b -> (int) corners.stream()
                                .filter(c -> validRectangle(inputGrid, a, b, c))
                                .count())
                        .sum())
                .sum();
    }
}