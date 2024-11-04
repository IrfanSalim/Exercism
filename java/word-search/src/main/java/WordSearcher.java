import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class WordSearcher {
    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        Map<String, Optional<WordLocation>> res = new HashMap<>();
        // generate possible directions
        Pair[] directions = new Pair[8];
        for (int i = 0; i < 9; i++)
            directions[i < 5 ? i : i - 1] = new Pair(i / 3 - 1, i % 3 - 1);

        for (String s : words) {
            res.put(s, Optional.empty());
            // find match for first character
            for (int x = 0; x < grid.length; x++)
                for (int y = 0; y < grid[0].length; y++)
                    if (s.charAt(0) == grid[x][y]) {
                        int len = s.length();
                        for (Pair pt : directions) {
                            int x1 = x;
                            int y1 = y;
                            StringBuilder stringBuilder = new StringBuilder(String.valueOf(grid[x1][y1]));
                            for (int i = 1; i < len; i++) {
                                x1 += pt.getX();
                                y1 += pt.getY();
                                if (x1 < 0 || y1 < 0 || x1 >= grid.length || y1 >= grid[x1].length
                                        || grid[x1][y1] != s.charAt(i))
                                    break;
                                stringBuilder.append(grid[x1][y1]);
                            }
                            if (stringBuilder.length() == len && s.equals(stringBuilder.toString()))
                                res.put(s, Optional
                                        .of(new WordLocation(new Pair(y + 1, x + 1), new Pair(y1 + 1, x1 + 1))));
                        }
                    }
        }
        return res;
    }
}
