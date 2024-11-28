public class WordProblemSolver {

    public int solve(String input) {
        if (!input.matches("^What is -?\\d+( (plus|minus|multiplied by|divided by) (-?\\d+))*\\?$")) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }
        String[] entries = input.replaceAll("(What is | by|\\?)", "").split(" ");

        int result = Integer.parseInt(entries[0]);

        for (int i = 1; i + 1 < entries.length; i += 2) {
            switch (entries[i]) {
                case "plus" -> result += Integer.parseInt(entries[i + 1]);
                case "minus" -> result -= Integer.parseInt(entries[i + 1]);
                case "multiplied" -> result *= Integer.parseInt(entries[i + 1]);
                case "divided" -> result /= Integer.parseInt(entries[i + 1]);
            }
        }
        return result;
    }
}
