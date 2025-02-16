import java.util.ArrayList;

class RectangleCounter {
    String[] grid;

    int countRectangles(String[] grid) {
        this.grid = grid;
        ArrayList<Integer> row = new ArrayList<Integer>();
        ArrayList<Integer> col = new ArrayList<Integer>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length(); j++)
                if (grid[i].charAt(j) == '+') {
                    row.add(i);
                    col.add(j);
                }
        int result = 0;
        for (int i = 0; i < row.size(); i++) {
            int x0 = row.get(i);
            int y0 = col.get(i);
            for (int j = 0; j < row.size(); j++) {
                int x1 = row.get(j);
                int y1 = col.get(j);
                if (x0 < x1 && y0 < y1)
                    if (checkRow(x0, y0, y1) && checkRow(x1, y0, y1) && checkCol(y0, x0, x1) && checkCol(y1, x0, x1))
                        result++;
            }
        }
        return result;
    }

    boolean checkRow(int x, int y0, int y1) {
        for (int i = y0; i <= y1; i++)
            if ("-+".indexOf(grid[x].charAt(i)) == -1)
                return false;
        return true;
    }

    boolean checkCol(int y, int x0, int x1) {
        for (int i = x0; i <= x1; i++)
            if ("|+".indexOf(grid[i].charAt(y)) == -1)
                return false;
        return true;
    }

}