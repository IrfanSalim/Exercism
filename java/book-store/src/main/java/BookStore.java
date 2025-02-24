// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.stream.Collectors;

// public class BookStore {

//     static final int PRICE = 8;
//     static final double[] RATIOS = { -1, 1, 0.95, 0.9, 0.8, 0.75 };

//     public double calculateBasketCost(List<Integer> books) {
//         double total = 0.0;
//         List<Integer> cart = new ArrayList<>(books);
//         int cartCount = cart.size();
//         List<Integer> groups = new ArrayList<>();

//         while (cartCount > 0) {
//             // find max group of distinct books
//             List<Integer> distinct = cart.stream()
//                     .distinct()
//                     .collect(Collectors.toList());
//             int c = distinct.size();

//             // if we find a group of 3 and we have previously
//             // found a group of 5, remove the 5 group and add
//             // two groups of 4 as it is a better deal.
//             if (c == 3 && groups.contains(5)) {
//                 groups.remove(Integer.valueOf(5));
//                 groups.addAll(Arrays.asList(4, 4));
//             } else {
//                 groups.add(c);
//             }

//             // remove the distinct books that we found from the cart.
//             for (Integer d : distinct) {
//                 cart.remove(d);
//             }

//             // update the cart count
//             cartCount = cart.size();
//         }

//         for (Integer group : groups) {
//             total += group * PRICE * RATIOS[group];
//         }
//         return total;
//     }
// }

import java.util.*;

public class BookStore {
    public double calculateBasketCost(List<Integer> booksInStore) {
        int[] books = new int[5];
        booksInStore.forEach(i -> books[--i]++);
        Arrays.sort(books);
        int group1 = books[2] - books[1];
        int group2 = books[1] - books[0];
        int combined = Math.min(group1, books[0]);
        group1 -= combined;
        books[0] -= combined;
        group2 += 2 * combined;
        return 8.0
                * (books[4] - books[3] + 1.9 * (books[3] - books[2]) + 2.7 * group1 + 3.2 * group2 + 3.75 * books[0]);
    }
}