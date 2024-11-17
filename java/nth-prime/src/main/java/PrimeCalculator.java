// import java.util.ArrayList;
// import java.util.List;

// class PrimeCalculator {

//     int nth(int nth) {
//         if (nth < 1)
//             throw new IllegalArgumentException();
//         List<Integer> primeList = new ArrayList<>();
//         primeList.add(2);
//         primeList.add(3);

//         for (int i = 5; primeList.size() < nth; i++) {
//             boolean isPrime = true;
//             for (int j = 2; j <= Math.sqrt(i); j++) {
//                 if (i % j == 0)
//                     isPrime = false;
//             }
//             if (isPrime)
//                 primeList.add(i);
//         }
//         return primeList.get(nth - 1);
//     }

// }

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PrimeCalculator {

    // infinite stream
    private Stream<Integer> seqNumbers = Stream.iterate(2, i -> i + 1);

    int nth(int nth) {
        if (nth < 1)
            throw new IllegalArgumentException();

        // can use skip(n-1) or max() or reduce(first, second => second)
        return seqNumbers.filter(this::isPrime).limit(nth).max(Comparator.comparing(Integer::intValue)).orElse(0);
    }

    private boolean isPrime(Integer integer) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(integer))
                .noneMatch(ele -> integer % ele == 0);
    }

}