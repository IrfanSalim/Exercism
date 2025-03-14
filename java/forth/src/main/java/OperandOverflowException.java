package main.java;

import java.util.HashMap;

public class OperandOverflowException extends IllegalArgumentException {

    private static final String FORMAT = "%s requires that the stack contain at least %d %s";
    private static final HashMap<String, String> OPERANDS;
    private static final HashMap<String, String> MONO_OPERANDS;

    static {
        HashMap<String, String> BI_OPERANDS = new HashMap<>(6);
        BI_OPERANDS.put("+", "Addition");
        BI_OPERANDS.put("-", "Subtraction");
        BI_OPERANDS.put("*", "Multiplication");
        BI_OPERANDS.put("/", "Division");
        BI_OPERANDS.put("swap", "Swapping");
        BI_OPERANDS.put("over", "Overing");

        MONO_OPERANDS = new HashMap<>(2);
        MONO_OPERANDS.put("dup", "Duplicating");
        MONO_OPERANDS.put("drop", "Dropping");

        OPERANDS = new HashMap<>();
        OPERANDS.putAll(MONO_OPERANDS);
        OPERANDS.putAll(BI_OPERANDS);
    }

    public OperandOverflowException(String operand) {
        super(String.format(FORMAT,
                OPERANDS.getOrDefault(operand, ""),
                MONO_OPERANDS.containsKey(operand) ? 1 : 2,
                MONO_OPERANDS.containsKey(operand) ? "value" : "values"));
    }
}