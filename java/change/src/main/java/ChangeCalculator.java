// import java.util.List;
// import java.util.*;

// class ChangeCalculator {
//     final List<Integer> denominations;

//     ChangeCalculator(List<Integer> currencyCoins) {
//         denominations = currencyCoins;
//     }

//     List<Integer> computeMostEfficientChange(int grandTotal) {
//         if (grandTotal < 0) {
//             throw new IllegalArgumentException("Negative totals are not allowed.");
//         }
//         ArrayList[] minCoins = new ArrayList[grandTotal + 1];
//         minCoins[0] = new ArrayList<>();
//         for (int i = 1; i <= grandTotal; i++) {
//             minCoins[i] = new ArrayList<>();
//             int minSize = Integer.MAX_VALUE;
//             int minSizeIndex = -1;
//             boolean isDenomination = false;
//             for (int denomination : denominations) {
//                 if (denomination == i) {
//                     minCoins[i].add(denomination);
//                     isDenomination = true;
//                     break;
//                 } else if (denomination < i) {
//                     if (minCoins[i - denomination].size() > 0 &&
//                             minCoins[i - denomination].size() < minSize) {
//                         minSize = minCoins[i - denomination].size();
//                         minSizeIndex = i - denomination;
//                     }
//                 }
//             }
//             if (!isDenomination && minSizeIndex != -1) {
//                 minCoins[i] = new ArrayList<>(minCoins[minSizeIndex]);
//                 minCoins[i].add(i - minSizeIndex);
//             }
//         }
//         if (grandTotal > 0 && minCoins[grandTotal].isEmpty()) {
//             throw new IllegalArgumentException(
//                     "The total " + grandTotal + " cannot be represented in the given currency.");
//         } else {
//             Collections.sort(minCoins[grandTotal]);
//             return minCoins[grandTotal];
//         }
//     }

// }

import java.util.*;

final class ChangeCalculator {
    private final List<Integer> coins;

    public ChangeCalculator(List<Integer> coins) {
        this.coins = coins;
    }

    public List<Integer> computeMostEfficientChange(int target) {
        if (target < 0) {
            throw new IllegalArgumentException("Negative totals are not allowed.");
        }
        if (target > 0 && target < coins.get(0)) {
            throw new IllegalArgumentException("The total " + target + " cannot be represented in the given currency.");
        }
        if (target == 0) {
            return new LinkedList<>();
        }
        Map<Integer, List<Integer>> minimalCoins = new HashMap<>();
        minimalCoins.put(0, new LinkedList<>());
        for (int i = 1; i <= target; i++) {
            final int amount = i;
            List<Integer> minimalCoinsForAmount = coins
                    .stream()
                    .filter(c -> c <= amount)
                    .map(c -> prepend(c, minimalCoins.get(amount - c)))
                    .filter(c -> c.stream().mapToInt(j -> j).sum() == amount)
                    .sorted(Comparator.comparingInt(List::size))
                    .findFirst()
                    .orElse(new LinkedList<>());
            minimalCoins.put(amount, minimalCoinsForAmount);
        }
        List<Integer> mostEfficientChange = minimalCoins.get(target);
        if (mostEfficientChange.size() == 0) {
            throw new IllegalArgumentException("The total " + target + " cannot be represented in the given currency.");
        }
        return mostEfficientChange;
    }

    private static List<Integer> prepend(int coin, List<Integer> coins) {
        List<Integer> newCoins = new LinkedList<>(coins);
        newCoins.add(0, coin);
        return newCoins;
    }
}