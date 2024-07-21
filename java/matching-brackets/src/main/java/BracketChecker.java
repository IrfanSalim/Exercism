import java.util.*;

class BracketChecker {

    String expression;

    BracketChecker(String expression) {
        this.expression = expression;
    }

    boolean areBracketsMatchedAndNestedCorrectly() {
        char[] chars = expression.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.push(chars[i]);
            } else if (chars[i] == ')' || chars[i] == ']' || chars[i] == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (isMatchingPair(stack.peek(), chars[i])) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    boolean isMatchingPair(Character open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
    }

}


import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Chars;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

final class BracketChecker {
    private static Map brackets = ImmutableMap.of('[',']','{','}','(',')');
    private String string;

    public BracketChecker(String input) {
        string = input;
    }

    public boolean areBracketsMatchedAndNestedCorrectly() {
        //stack of opened brackets
        List stack = new LinkedList<Character>();
        return (Chars.asList(string.toCharArray())
                .stream()
                .mapToInt(x -> {
                    //if closing bracket
                    if (brackets.containsValue(x)) {
                        //no opened - mistake!
                        if (stack.size() == 0) return 1;
                        //wrong last opened - mistake
                        if (stack.remove(stack.size()-1) != x) return 1;
                    }
                    //opening bracket
                    if (brackets.containsKey(x)) {
                        stack.add(brackets.get(x));
                    }
                    return 0; //all is ok
                })
                .sum() == 0 // no mistakes
        ) & (stack.isEmpty()    // and all brackets are closed
        );
    }
}
