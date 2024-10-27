class RotationalCipher {
    private final int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        StringBuilder result = new StringBuilder();

        for (char ch : data.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int shifted = (ch - base + shiftKey) % 26 + base;
                result.append((char) shifted);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}