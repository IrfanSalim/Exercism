import java.util.*;
import java.util.stream.Collectors;
import main.java.OperandOverflowException;

public class ForthEvaluator {
    public List<Integer> evaluateProgram(List<String> program) {
        final Stack<Integer> stack = new Stack<>();
        final Map<String, String> functions = new HashMap<>();

        for (String instructions : program) {
            final String[] tokens = instructions.split(" ");

            if (instructions.startsWith(":") && instructions.endsWith(";")) {
                defineFunction(functions, tokens);
            } else {
                executeMultiple(stack, functions, tokens);
            }
        }

        return stack;
    }

    private void defineFunction(Map<String, String> functions, String[] tokens) {
        LinkedList<String> tokensList = new LinkedList<>(Arrays.asList(tokens));
        tokensList.removeFirst(); // :
        tokensList.removeLast(); // ;

        String functionName = tokensList.removeFirst();

        if (parseInteger(new Stack<>(), functionName))
            throw new IllegalArgumentException("Cannot redefine numbers");

        functions.put(functionName.toLowerCase(), overrideFunctions(tokensList, functions));
    }

    private boolean parseInteger(Stack<Integer> stack, String token) {
        try {
            stack.push(Integer.parseInt(token));
            return true;
        } catch (NumberFormatException ignore) {
        }

        return false;
    }

    private String overrideFunctions(List<String> ins, Map<String, String> functions) {
        return ins.stream()
                .map(str -> functions.getOrDefault(str, str))
                .collect(Collectors.joining(" "));
    }

    private void executeMultiple(Stack<Integer> stack, Map<String, String> functions, String[] tokens) {
        Arrays.stream(tokens).forEachOrdered(token -> executeSingle(stack, functions, token));
    }

    private void executeSingle(Stack<Integer> stack, Map<String, String> functions, final String token) {
        if (parseInteger(stack, token))
            return;

        final String tokenLowerCase = token.toLowerCase();

        try {
            if (functions.containsKey(tokenLowerCase)) {
                executeMultiple(stack, functions, functions.get(tokenLowerCase).split(" "));
            } else if ("+-*/ swap over".contains(tokenLowerCase)) {
                int a = stack.pop(), b = stack.pop();

                switch (tokenLowerCase) {
                    case "+":
                        stack.push(b + a);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "*":
                        stack.push(b * a);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                    case "swap":
                        stack.push(a);
                        stack.push(b);
                        break;
                    case "over":
                        stack.push(b);
                        stack.push(a);
                        stack.push(b);
                        break;
                }
            } else if ("dup".equals(tokenLowerCase)) {
                stack.push(stack.peek());
            } else if ("drop".equals(tokenLowerCase)) {
                stack.pop();
            } else {
                throw new IllegalArgumentException(
                        "No definition available for operator \"" + token + "\"");
            }
        } catch (EmptyStackException ignore) {
            throw new OperandOverflowException(token);
        } catch (ArithmeticException ignore) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }
    }
}