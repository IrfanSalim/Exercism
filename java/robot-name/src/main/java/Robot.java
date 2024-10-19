import java.util.*;

public class Robot {
    private static char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Stack<String> unusedNames = generateNames();

    private static Stack<String> generateNames() {
        List<String> allNames = new ArrayList<>();

        for (char firstLetter : LETTERS) {
            for (char secondLetter : LETTERS) {
                for (int i = 0; i < 1000; i++) {
                    allNames.add(firstLetter + "" + secondLetter + String.format("%03d", i));
                }
            }
        }

        Collections.shuffle(allNames);
        Stack<String> shuffledNames = new Stack<>();
        shuffledNames.addAll(allNames);
        return shuffledNames;
    }

    private String name;

    public Robot() {
        reset();
    }

    public void reset() throws EmptyStackException {
        this.name = unusedNames.pop();
    }

    public String getName() {
        return name;
    }

    public static void resetNames() {
        unusedNames = generateNames();
    }
}