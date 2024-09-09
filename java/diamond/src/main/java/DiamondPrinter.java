import java.util.*;

class DiamondPrinter {

    List<String> printToList(char a) {
        List<String> list = new ArrayList<>();
        int size = a - 'A'; // Determines the number of rows in the top half

        // Generate the top half including the middle row
        for (int i = 0; i <= size; i++) {
            StringBuilder sb = new StringBuilder();
            // Leading spaces
            for (int j = 0; j < size - i; j++) {
                sb.append(" ");
            }
            // First character
            sb.append((char) ('A' + i));
            // Spaces between characters
            if (i > 0) {
                for (int k = 0; k < 2 * i - 1; k++) {
                    sb.append(" ");
                }
                // Second character
                sb.append((char) ('A' + i));
            }
            // Trailing spaces
            for (int j = 0; j < size - i; j++) {
                sb.append(" ");
            }
            list.add(sb.toString());
        }

        // Generate the bottom half
        for (int i = size - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            // Leading spaces
            for (int j = 0; j < size - i; j++) {
                sb.append(" ");
            }
            // First character
            sb.append((char) ('A' + i));
            // Spaces between characters
            if (i > 0) {
                for (int k = 0; k < 2 * i - 1; k++) {
                    sb.append(" ");
                }
                // Second character
                sb.append((char) ('A' + i));
            }
            // Trailing spaces
            for (int j = 0; j < size - i; j++) {
                sb.append(" ");
            }
            list.add(sb.toString());
        }

        return list;
    }

}


import java.util.Arrays;
import java.util.List;

public class DiamondPrinter {

    public List<String> printToList(char letter) {
        int number = letter - 'A' + 1;
        int size = 2 * (number - 1) + 1;

        String[] lines = new String[size];
        for (int i=0; i<size / 2 + 1; i++) {
            char c = (char) ('A' + i);

            int leftPosition = size / 2 + 1 - i - 1;
            int rightPosition = size - leftPosition - 1;
            char[] buffer = new char[size];
            Arrays.fill(buffer, ' ');
            buffer[leftPosition] = c;
            buffer[rightPosition] = c;
            String line = new String(buffer);

            lines[i] = line;
            lines[size - 1 - i] = line;
        }

        return Arrays.asList(lines);
    }

}


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class DiamondPrinter {
    public static final char FROM = 'A';

    public static String line(int x, int y) {
        final int i = x - FROM;
        return IntStream.rangeClosed(FROM - i, FROM + i)
                .map(j -> FROM + Math.abs(FROM - j) == y? y: ' ')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
    }

    public List<String> printToList(char x) {
        return IntStream.concat(
                IntStream.rangeClosed(FROM, x),
                IntStream.iterate(x - 1, y -> y - 1).limit(x - FROM))
                .mapToObj(y -> line(x, y))
                .collect(Collectors.toList());
    }
}