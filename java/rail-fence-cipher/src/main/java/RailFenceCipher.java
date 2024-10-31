// public class RailFenceCipher {

//     private int rails;

//     public RailFenceCipher(int rails) {
//         this.rails = rails;
//     }

//     public String getEncryptedData(String input) {

//         char[][] encoded = makeRails(input);
//         StringBuilder encrypted = new StringBuilder();
//         for (char[] encodedRow : encoded) {
//             for (char encodedVal : encodedRow) {
//                 if (encodedVal != '\u0000') {
//                     encrypted.append(encodedVal);
//                 }
//             }
//         }

//         return encrypted.toString();

//     }

//     public String getDecryptedData(String input) {
//         char[][] railed = makeRails(input);
//         StringBuilder decrypted = new StringBuilder();
//         int y = 0;
//         for (int i = 0; i < rails; i++) {
//             for (int x = 0; x < input.length(); x++) {
//                 if (railed[i][x] != '\u0000') {
//                     railed[i][x] = input.charAt(y++);
//                 }
//             }
//         }

//         boolean down = true;
//         for (int i = 0, x = 0; i < input.length(); i++) {

//             decrypted.append(railed[x][i]);

//             if (x == rails - 1) {
//                 down = false;
//             } else if (x == 0) {
//                 down = true;
//             }
//             x = down ? x + 1 : x - 1;
//         }

//         return decrypted.toString();

//     }

//     private char[][] makeRails(String input) {
//         char[][] encoded = new char[rails][input.length()];
//         boolean down = true;
//         for (int i = 0, x = 0, y = 0; i < input.length(); i++) {

//             encoded[x][i] = input.charAt(i);

//             if (x == rails - 1) {
//                 down = false;
//             } else if (x == 0) {
//                 down = true;
//             }
//             x = down ? x + 1 : x - 1;
//         }

//         return encoded;
//     }
// }

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class RailFenceCipher {

    private final int rails;
    private final int amplitude;

    RailFenceCipher(final int rails) {
        this.rails = rails;
        this.amplitude = (rails - 1) * 2;
    }

    String getEncryptedData(final String message) {
        return IntStream.range(0, message.length()).boxed()
                .sorted(Comparator.comparingInt(this::getRail))
                .map(i -> message.substring(i, i + 1))
                .collect(Collectors.joining());
    }

    String getDecryptedData(final String message) {
        return Stream.iterate(this.getEncryptedData(message),
                s -> !s.equals(message),
                s -> s = this.getEncryptedData(s))
                .reduce((a, b) -> b)
                .orElse("");
    }

    private int getRail(final int i) {
        final int j = i % this.amplitude;
        return j < rails ? j : this.amplitude - j;
    }

}