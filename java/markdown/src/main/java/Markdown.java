import java.util.stream.IntStream;

class Markdown {

    private boolean activeList = false;

    String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            if (line.startsWith("*")) {
                if (!activeList)
                    startList(result);
                result.append(parseListItem(line));
            } else {
                if (activeList)
                    endList(result);
                result.append(line.startsWith("#") ? parseHeader(line) : parseParagraph(line));
            }
        }

        if (activeList)
            endList(result);

        return result.toString();
    }

    private void startList(StringBuilder result) {
        activeList = true;
        result.append("<ul>");
    }

    private void endList(StringBuilder result) {
        activeList = false;
        result.append("</ul>");
    }

    private String parseHeader(String markdown) {
        int count = (int) IntStream.iterate(0, i -> i < markdown.length() && markdown.charAt(i) == '#', i -> i + 1)
                .count();

        if (count == 0) {
            return null;
        } else if (count > 6) {
            return parseParagraph(markdown);
        } else {
            return surround(markdown.substring(count + 1), "h" + count);
        }
    }

    private String parseListItem(String markdown) {
        return surround(parseSomeSymbols(markdown.substring(2)), "li");
    }

    private String parseParagraph(String markdown) {
        return surround(parseSomeSymbols(markdown), "p");
    }

    private String surround(String markdown, String tag) {
        return "<" + tag + ">" + markdown + "</" + tag + ">";
    }

    private String parseSomeSymbols(String markdown) {
        return markdown
                .replaceAll("__(.+)__", "<strong>$1</strong>")
                .replaceAll("_(.+)_", "<em>$1</em>");
    }
}