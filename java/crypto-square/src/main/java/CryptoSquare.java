class CryptoSquare {

    private StringBuilder text;
    private int size = 0, columns = 1;

    CryptoSquare(String s) {
        text = new StringBuilder();

        for (char c : s.toLowerCase().toCharArray())
            if (Character.isLetterOrDigit(c)) {
                text.append(c);
                ++size;
            }

        if (size < 2)
            return;

        columns = (int) Math.ceil(Math.sqrt(size));

        while (size % columns != 0) {
            text.append(' ');
            ++size;
        }
    }

    String getCiphertext() {
        if (size < 2)
            return text.toString();

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            int pos = i;
            while (pos < size) {
                res.append(text.charAt(pos));
                pos += columns;
            }
            res.append(' ');
        }
        return res.substring(0, res.length() - 1).toString();
    }
}