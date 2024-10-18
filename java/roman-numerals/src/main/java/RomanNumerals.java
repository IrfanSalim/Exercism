// public class RomanNumerals {
//     private static final String[] ROMAN_NUMBERS = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV",
//             "I" };
//     private static final int[] ARABIC_NUMBERS = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
//     private int number;

//     public RomanNumerals(int number) {
//         this.number = number;
//     }

//     public String getRomanNumeral() {
//         StringBuilder roman = new StringBuilder();
//         int remainedValue = number;
//         for (int i = 0; i < ARABIC_NUMBERS.length; i++) {
//             while (remainedValue >= ARABIC_NUMBERS[i]) {
//                 remainedValue -= ARABIC_NUMBERS[i];
//                 roman.append(ROMAN_NUMBERS[i]);
//             }
//         }
//         return roman.toString();
//     }
// }

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RomanNumerals {
    private final static Map<Integer, String> map = new LinkedHashMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    private String s;

    public RomanNumerals(int num) {
        final AtomicInteger n = new AtomicInteger(num);
        s = map.entrySet().stream().map(e -> {
            String s = e.getValue().repeat(n.get() / e.getKey());
            n.set(n.get() % e.getKey());
            return s;
        }).collect(Collectors.joining());
    }

    public String getRomanNumeral() {
        return s;
    }
}