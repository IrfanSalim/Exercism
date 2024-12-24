// class CryptoSquare {

//     private StringBuilder text;
//     private int size = 0, columns = 1;

//     CryptoSquare(String s) {
//         text = new StringBuilder();

//         for (char c : s.toLowerCase().toCharArray())
//             if (Character.isLetterOrDigit(c)) {
//                 text.append(c);
//                 ++size;
//             }

//         if (size < 2)
//             return;

//         columns = (int) Math.ceil(Math.sqrt(size));

//         while (size % columns != 0) {
//             text.append(' ');
//             ++size;
//         }
//     }

//     String getCiphertext() {
//         if (size < 2)
//             return text.toString();

//         StringBuilder res = new StringBuilder();
//         for (int i = 0; i < columns; i++) {
//             int pos = i;
//             while (pos < size) {
//                 res.append(text.charAt(pos));
//                 pos += columns;
//             }
//             res.append(' ');
//         }
//         return res.substring(0, res.length() - 1).toString();
//     }
// }

import static java.util.stream.Collectors.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class CryptoSquare {

    private final String ciphertext;

    CryptoSquare(final String text) {

        final String normalizedText = text.replaceAll("\\W", "").toLowerCase();

        final int rowLength = (int) Math.ceil(Math.sqrt(normalizedText.length()));

        final String[][] matrix = Stream.of(normalizedText.split("(?<=\\G.{" + rowLength + "})"))
                .map(row -> row.split("")).toArray(String[][]::new);

        final String[][] transposedMatrix = IntStream.range(0, rowLength)
                .mapToObj(i -> Stream.of(matrix).map(row -> i < row.length ? row[i] : " ").toArray(String[]::new))
                .toArray(String[][]::new);

        this.ciphertext = Stream.of(transposedMatrix)
                .map(row -> String.join("", row))
                .collect(joining(" "));
    }

    String getCiphertext() {
        return ciphertext;
    }

}