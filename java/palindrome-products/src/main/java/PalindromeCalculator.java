// import java.util.List;
// import java.util.Arrays;
// import java.util.ArrayList;
// import java.util.TreeMap;
// import java.util.SortedMap;

// public class PalindromeCalculator {
//     public SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int a, int b) {
//         if (a > b)
//             throw new IllegalArgumentException("invalid input: min must be <= max");

//         SortedMap<Long, List<List<Integer>>> result = new TreeMap<>();

//         for (int i = a; i <= b; i++) {
//             for (int j = i; j <= b; j++) {
//                 final long product = i * j;
//                 if (isPalindrome(String.valueOf(product)))
//                     result.computeIfAbsent(product, p -> new ArrayList<>()).add(Arrays.asList(i, j));
//             }
//         }

//         return result;
//     }

//     private static boolean isPalindrome(String s) {
//         for (int i = 0, j = s.length() - 1; i <= s.length() / 2; i++, j--) {
//             if (s.charAt(i) != s.charAt(j)) {
//                 return false;
//             }
//         }

//         return true;
//     }
// }

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PalindromeCalculator {
    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        if (minFactor > maxFactor) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }

        return IntStream.rangeClosed(minFactor, maxFactor)
                .mapToObj(second -> IntStream.rangeClosed(minFactor, second)
                        .mapToObj(first -> new Pair(first, second)))
                .flatMap(stream -> stream)
                .filter(pair -> {
                    String product = Long.toString((long) pair.first * pair.second);
                    return product.equals(new StringBuilder(product).reverse().toString());
                })
                .collect(Collectors.toMap(
                        pair -> (long) pair.first * pair.second,
                        pair -> List.of(List.of(pair.first, pair.second)),
                        (list1, list2) -> Stream.concat(list1.stream(), list2.stream()).toList(),
                        TreeMap::new));
    }

    private record Pair(int first, int second) {
    }
}