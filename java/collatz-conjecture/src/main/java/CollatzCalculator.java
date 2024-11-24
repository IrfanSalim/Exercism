// class CollatzCalculator {

//     int computeStepCount(int start) {
//         if (start <= 0) {
//             throw new IllegalArgumentException("Only positive integers are allowed");
//         }
//         int steps = 0;

//         while (start != 1) {
//             start = (start % 2 == 0) ? start / 2 : start * 3 + 1;
//             steps++;
//         }
//         return steps;
//     }

// }

import java.util.stream.IntStream;

class CollatzCalculator {

    long computeStepCount(int start) {
        if (start < 1) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }

        return IntStream.iterate(start, num -> num != 1, num -> (num & 1) == 1 ? 3 * num + 1 : num >> 1).count();
    }
}