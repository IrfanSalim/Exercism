import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cipher {
    private String key;

    public Cipher(String key) {
        this.key = key;
    }

    public Cipher() {
        this(IntStream.range(0, 100)
                .mapToObj(i -> Character.toString('a' + (int) (Math.random() * 26)))
                .collect(Collectors.joining("")));
    }

    public String getKey() {
        return key;
    }

    public String encode(String plainText) {
        return translate(plainText, +1);
    }

    public String decode(String substring) {
        return translate(substring, -1);
    }

    private String translate(String plainText, int sign) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            result.append(
                    (char) ((plainText.charAt(i) - 'a' + sign * (key.charAt(i % key.length()) - 'a') + 26) % 26 + 'a'));
        }
        return result.toString();
    }
}