
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.IntStream;

// public class OpticalCharacterReader {
//     private static final Map<Integer, String> FIGURES = new HashMap<>();
//     private static final Map<Integer, Character> SEGMENTS = new HashMap<>();
//     static {
//         FIGURES.put(0b111101010, "0");
//         FIGURES.put(0b100100000, "1");
//         FIGURES.put(0b011110010, "2");
//         FIGURES.put(0b110110010, "3");
//         FIGURES.put(0b100111000, "4");
//         FIGURES.put(0b110011010, "5");
//         FIGURES.put(0b111011010, "6");
//         FIGURES.put(0b100100010, "7");
//         FIGURES.put(0b111111010, "8");
//         FIGURES.put(0b110111010, "9");
//         SEGMENTS.put(1, '_');
//         SEGMENTS.put(3, '|');
//         SEGMENTS.put(4, '_');
//         SEGMENTS.put(5, '|');
//         SEGMENTS.put(6, '|');
//         SEGMENTS.put(7, '_');
//         SEGMENTS.put(8, '|');
//     }

//     public OpticalCharacterReader() {
//     }

//     public String parse(List<String> lines) {
//         if (lines.size() % 4 != 0) {
//             throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
//         }
//         if (lines.get(0).length() % 3 != 0) {
//             throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
//         }
//         String result = "";
//         for (int i = 0; i < lines.size() / 4; i++) {
//             if (!result.isEmpty()) {
//                 result = result + ",";
//             }
//             int count = 4 * i;
//             final List<String> ls = lines.stream().skip(count).limit(4).toList();
//             result = result + parseLine(ls);
//         }
//         return result;
//     }

//     private static String parseLine(List<String> lines) {
//         String result = "";
//         for (int i = 0; i < lines.get(0).length() / 3; i++) {
//             final int pos = 3 * i;
//             final int[] segments = lines.stream().limit(3)
//                     .flatMapToInt(l -> l.chars().skip(pos).limit(3))
//                     .toArray();
//             result = result + segmentsToString(segments);
//         }
//         return result;
//     }

//     private static String segmentsToString(int[] segments) {
//         return FIGURES.getOrDefault(IntStream.range(0, 9)
//                 .map(i -> segmentOn(segments[i], i) ? (int) Math.pow(2, i) : 0)
//                 .sum(), "?");
//     }

//     private static boolean segmentOn(int c, int segPos) {
//         if (c == ' ') {
//             return false;
//         }
//         return c == SEGMENTS.get(segPos);
//     }
// }

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class OpticalCharacterReader {
    String parse(List<String> input) {
        if (input.size() % 4 != 0) {
            throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        }
        if (input.stream().anyMatch(line -> line.length() % 3 != 0)) {
            throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
        }
        return IntStream.range(0, input.size() / 4)
                .mapToObj(idx -> input.subList(idx * 4, idx * 4 + 4))
                .map(quadLine -> IntStream.range(0, quadLine.get(0).length() / 3)
                        .mapToObj(idx -> quadLine
                                .stream()
                                .map(line -> line.substring(idx * 3, idx * 3 + 3))
                                .collect(Collectors.joining()))
                        .map(this::parse)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(","));
    }

    private String parse(String digit) {
        return switch (digit) {
            case " _ | ||_|   " -> "0";
            case "     |  |   " -> "1";
            case " _  _||_    " -> "2";
            case " _  _| _|   " -> "3";
            case "   |_|  |   " -> "4";
            case " _ |_  _|   " -> "5";
            case " _ |_ |_|   " -> "6";
            case " _   |  |   " -> "7";
            case " _ |_||_|   " -> "8";
            case " _ |_| _|   " -> "9";
            default -> "?";
        };
    }
}