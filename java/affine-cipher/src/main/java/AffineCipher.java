import java.math.BigInteger;
import java.util.stream.Collectors;

public class AffineCipher {

    private String ERROR = "Error: keyA and alphabet size must be coprime.";

    private static int M = 26;
    private static int φM = 12;

    private static int ASCII_A = 'a';

    public String encode(String text, int a, int b) {
        if (!BigInteger.valueOf(a).gcd(BigInteger.valueOf(M)).equals(BigInteger.ONE)) {
            throw new IllegalArgumentException(ERROR);
        }

        StringBuilder result = new StringBuilder();
        var tail = text.toLowerCase().chars()
                .filter(Character::isLetterOrDigit)
                .map(c -> Character.isDigit(c) ? c : Math.floorMod(a * (c - ASCII_A) + b, M) + ASCII_A)
                .mapToObj(Character::toString)
                .reduce("", (str, nextChar) -> {
                    if (str.length() == 5) {
                        result.append(str);
                        result.append(" ");
                        str = "";
                    }
                    return str + nextChar;
                });

        return result.append(tail).toString();

    }

    public String decode(String cipher, int a, int b) {
        if (!BigInteger.valueOf(a).gcd(BigInteger.valueOf(M)).equals(BigInteger.ONE)) {
            throw new IllegalArgumentException(ERROR);
        }

        int inverse = Math.floorMod((long) Math.pow(a, φM - 1), M);
        return cipher.chars()
                .filter(Character::isLetterOrDigit)
                .map(c -> Character.isDigit(c) ? c : Math.floorMod(inverse * (c - ASCII_A - b), M) + ASCII_A)
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }
}