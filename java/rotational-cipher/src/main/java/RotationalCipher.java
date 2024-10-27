// class RotationalCipher {
//     private final int shiftKey;

//     RotationalCipher(int shiftKey) {
//         this.shiftKey = shiftKey;
//     }

//     String rotate(String data) {
//         StringBuilder result = new StringBuilder();

//         for (char ch : data.toCharArray()) {
//             if (Character.isLetter(ch)) {
//                 char base = Character.isUpperCase(ch) ? 'A' : 'a';
//                 int shifted = (ch - base + shiftKey) % 26 + base;
//                 result.append((char) shifted);
//             } else {
//                 result.append(ch);
//             }
//         }

//         return result.toString();
//     }
// }

class RotationalCipher {

    private int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    int rot(int ch) {
        if (!Character.isLetter(ch)) {
            return ch;
        }
        char first = Character.isUpperCase(ch) ? 'A' : 'a';
        return first + (ch + shiftKey - first) % 26;
    }

    String rotate(String data) {
        return data.codePoints()
                .mapToObj(this::rot)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}