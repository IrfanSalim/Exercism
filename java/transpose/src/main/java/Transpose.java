import java.util.Arrays;

public class Transpose {
    String transpose(String input) {
        String[] rows = input.split("\n");
        int max = Arrays.stream(rows).mapToInt(String::length).max().orElse(0);
        String[] columns = new String[max];

        int last = rows.length;
        for (int i = 0; i < max; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < last; j++) {
                char cell = rows[j].length() > i ? rows[j].charAt(i) : ' ';
                sb.append(cell);
            }
            columns[i] = sb.toString();
            while (last > 0 && rows[last - 1].length() <= i + 1) {
                last--;
            }
        }

        return String.join("\n", columns);
    }
}

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Transpose {

    String transpose(final String input) {

        final String[][] matrix = Stream.of(input.split("\\n"))
                .map(row -> row.split("")).toArray(String[][]::new);

        final int maxLength = Stream.of(matrix).mapToInt(i -> i.length).max().orElse(0);

        final String[][] transposedMatrix = IntStream.range(0, maxLength)
                .mapToObj(col -> Arrays.stream(matrix).map(row -> col < row.length ? row[col] : "")
                        .toArray(String[]::new))
                .toArray(String[][]::new);

        return Stream.of(transposedMatrix).map(row -> String.join("", this.pad(row))).collect(Collectors.joining("\n"));

    }

    private String[] pad(final String[] row) {
        final String[] paddedRow = new String[row.length];
        paddedRow[row.length - 1] = row[row.length - 1];
        for (int i = paddedRow.length - 2; i >= 0; i--) {
            paddedRow[i] = row[i].equals("") && !paddedRow[i + 1].equals("") ? " " : row[i];
        }
        return paddedRow;
    }
}
