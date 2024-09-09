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
