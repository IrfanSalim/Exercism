// import java.util.HashMap;
// import java.util.Map;
// import java.util.Optional;
// import java.util.Set;

// class WordSearcher {
//     Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
//         Map<String, Optional<WordLocation>> res = new HashMap<>();
//         // generate possible directions
//         Pair[] directions = new Pair[8];
//         for (int i = 0; i < 9; i++)
//             directions[i < 5 ? i : i - 1] = new Pair(i / 3 - 1, i % 3 - 1);

//         for (String s : words) {
//             res.put(s, Optional.empty());
//             // find match for first character
//             for (int x = 0; x < grid.length; x++)
//                 for (int y = 0; y < grid[0].length; y++)
//                     if (s.charAt(0) == grid[x][y]) {
//                         int len = s.length();
//                         for (Pair pt : directions) {
//                             int x1 = x;
//                             int y1 = y;
//                             StringBuilder stringBuilder = new StringBuilder(String.valueOf(grid[x1][y1]));
//                             for (int i = 1; i < len; i++) {
//                                 x1 += pt.getX();
//                                 y1 += pt.getY();
//                                 if (x1 < 0 || y1 < 0 || x1 >= grid.length || y1 >= grid[x1].length
//                                         || grid[x1][y1] != s.charAt(i))
//                                     break;
//                                 stringBuilder.append(grid[x1][y1]);
//                             }
//                             if (stringBuilder.length() == len && s.equals(stringBuilder.toString()))
//                                 res.put(s, Optional
//                                         .of(new WordLocation(new Pair(y + 1, x + 1), new Pair(y1 + 1, x1 + 1))));
//                         }
//                     }
//         }
//         return res;
//     }
// }

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class WordSearcher {
    public Map<String, Optional<WordLocation>> search(Set<String> words, char[][] chars) {
        return words.stream().collect(Collectors.toMap(Function.identity(), word -> findLocation(word, chars)));
    }

    private Optional<WordLocation> findLocation(String word, char[][] chars) {
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);
        List<Pair> firstCharCoordinates = findCoordinatesOfChar(firstChar, chars);
        List<Pair> lastCharCoordinates = findCoordinatesOfChar(lastChar, chars);
        if (firstCharCoordinates.isEmpty() || lastCharCoordinates.isEmpty())
            return Optional.empty();
        // get map where key is coordinate of start and value is potential end of string
        Map<Pair, Optional<Pair>> possibleLocations = firstCharCoordinates.stream()
                .collect(Collectors.toMap(Function.identity(),
                        begin -> lastCharCoordinates.stream()
                                .filter(end -> isAccessible(begin, end, word.length()))
                                .findFirst()));
        if (possibleLocations.isEmpty() || possibleLocations.values().stream().noneMatch(Optional::isPresent))
            return Optional.empty();
        return possibleLocations.entrySet().stream()
                .filter(e -> e.getValue().isPresent())
                .filter(e -> getWord(e.getKey(), e.getValue().get(), chars).equals(word)).findFirst()
                .map(e -> toWordLocation(e.getKey(), e.getValue().get()));

    }

    // because arrays have zerobased need to increment values of coordinates
    private WordLocation toWordLocation(Pair begin, Pair end) {
        return new WordLocation(new Pair(begin.getX() + 1, begin.getY() + 1), new Pair(end.getX() + 1, end.getY() + 1));
    }

    // returns word by coordinates
    private String getWord(Pair begin, Pair end, char[][] chars) {
        if (begin.getX() == end.getX()) { // word is vertically aligned
            return getWord(begin.getY(), end.getY(), column(chars, begin.getX()));
        }
        if (begin.getY() == end.getY()) {// word is horizontally aligned
            return getWord(begin.getX(), end.getX(), chars[begin.getY()]);
        }
        // word is aligned by diagonal
        return IntStream.iterate(begin.getY(), i -> i + (begin.getY() > end.getY() ? -1 : 1))
                .limit(abs(begin.getY() - end.getY()) + 1)
                .mapToObj(rowIdx -> new Pair(calculateNextX(begin, end, rowIdx), rowIdx))
                .map(pair -> chars[pair.getY()][pair.getX()]).map(ch -> Character.toString(ch))
                .collect(Collectors.joining());
    }

    // calculate next value of X using coordinates of word and current value of Y
    // (rowIdx)
    // need when word is aligned by diagonal
    private int calculateNextX(Pair begin, Pair end, int rowIdx) {
        return begin.getX() + abs(rowIdx - begin.getY()) * (begin.getX() > end.getX() ? -1 : 1);
    }

    // returns word from char arrays by idx of start and idx of end
    private String getWord(int start, int end, char[] arr) {
        return IntStream.iterate(start, i -> i + (start > end ? -1 : 1))
                .limit(abs(end - start) + 1).boxed().map(i -> arr[i]).map(ch -> Character.toString(ch))
                .collect(Collectors.joining());
    }

    // return column from 2 dimensional arrays of chars
    private char[] column(char[][] chars, int columnIdx) {
        char[] column = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            column[i] = chars[i][columnIdx];
        }
        return column;
    }

    // checks is end is accessible from begin horizontally, vertically or by
    // diagonal
    private boolean isAccessible(Pair begin, Pair end, int length) {
        if (begin.getX() == end.getX())
            return abs(begin.getY() - end.getY()) + 1 == length;
        if (begin.getY() == end.getY())
            return abs(begin.getX() - end.getX()) + 1 == length;
        return abs(begin.getX() - end.getX()) + 1 == length && abs(begin.getY() - end.getY()) + 1 == length;
    }

    // return all coordinates of char in 2 dimensional array
    private List<Pair> findCoordinatesOfChar(char c, char[][] chars) {
        return IntStream.range(0, chars.length)
                .mapToObj(row -> IntStream.range(0, chars[row].length)
                        .filter(column -> chars[row][column] == c).boxed()
                        .map(column -> new Pair(column, row)).collect(Collectors.toList()))
                .flatMap(Collection::stream).collect(Collectors.toList());
    }
}