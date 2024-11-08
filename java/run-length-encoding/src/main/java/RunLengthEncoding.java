import java.util.regex.*;

public class RunLengthEncoding {
    public String encode(String text) {
        StringBuilder builder = new StringBuilder();

        if (text.length() == 0) {
            return "";
        }

        char current = text.charAt(0);
        int group = 1;

        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != current) {
                if (group > 1) {
                    builder.append(group);
                }
                builder.append(current);
                current = text.charAt(i);
                group = 1;
            } else {
                group++;
            }

        }
        if (group > 1) {
            builder.append(group);
        }
        builder.append(current);

        return builder.toString();
    }

    public String decode(String text) {
        StringBuilder builder = new StringBuilder();
        Matcher m = Pattern.compile("(\\d*)(\\D)").matcher(text);
        while (m.find()) {
            if (!m.group(1).isEmpty()) {
                int size = Integer.parseInt(m.group(1));
                char c = m.group(2).charAt(0);
                for (int i = 0; i < size; i++) {
                    builder.append(c);
                }
            } else {
                builder.append(m.group());
            }
        }

        return builder.toString();
    }
}