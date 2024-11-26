// import java.util.*;

// public class BaseConverter {
//     private int value = 0;

//     public BaseConverter(int base, int[] number) {
//         if (base < 2)
//             throw new IllegalArgumentException("Bases must be at least 2.");

//         int l = number.length;
//         for (int i = 0; i < l; i++) {
//             int v = number[l - i - 1];
//             if (v >= base)
//                 throw new IllegalArgumentException("All digits must be strictly less than the base.");
//             if (v < 0)
//                 throw new IllegalArgumentException("Digits may not be negative.");
//             value += v * (int) Math.pow(base, i);
//         }
//     }

//     public List<Integer> convertToBase(int base) {
//         if (base < 2)
//             throw new IllegalArgumentException("Bases must be at least 2.");
//         if (value == 0)
//             return Arrays.asList(0);

//         LinkedList<Integer> result = new LinkedList<>();
//         while (value != 0) {
//             result.addFirst(value % base);
//             value /= base;
//         }
//         return result;
//     }

// }

import java.util.stream.IntStream;

class BaseConverter {

    private static final String NON_VALID_BASE_MESSAGE = "Bases must be at least 2.";
    private static final String DIGITS_LESS_THAN_BASE_MESSAGE = "All digits must be strictly less than the base.";
    private static final String NEGATIVE_DIGITS_MESSAGE = "Digits may not be negative.";

    private final int number;

    BaseConverter(final int base, final int[] digits) {

        if (base < 2) {
            throw new IllegalArgumentException(NON_VALID_BASE_MESSAGE);
        }

        for (int digit : digits) {
            if (digit >= base) {
                throw new IllegalArgumentException(DIGITS_LESS_THAN_BASE_MESSAGE);
            } else if (digit < 0) {
                throw new IllegalArgumentException(NEGATIVE_DIGITS_MESSAGE);
            }
        }

        this.number = IntStream.range(0, digits.length)
                .map(i -> digits[i] * (int) Math.pow(base, digits.length - i - 1.0))
                .sum();
    }

    int[] convertToBase(final int base) {

        if (base < 2) {
            throw new IllegalArgumentException(NON_VALID_BASE_MESSAGE);
        }

        if (this.number == 0) {
            return new int[] { 0 };
        }

        final int[] digits = IntStream.iterate(number, n -> n > 0, n -> n / base)
                .map(i -> i % base)
                .toArray();

        return this.reverse(digits);
    }

    private int[] reverse(final int[] array) {
        return IntStream.range(0, array.length)
                .map(i -> array[array.length - i - 1])
                .toArray();
    }

}