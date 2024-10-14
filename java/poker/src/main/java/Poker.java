// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// class Poker {

//     private List<String> hands;
//     private List<Integer> numericalValues;
//     private List<Integer> counts;

//     public Poker(List<String> hands) {
//         this.hands = hands;
//     }

//     public List<String> getBestHands() {
//         List<String> bestHands = new ArrayList<>();
//         bestHands.add(hands.get(0));

//         for (int i = 1; i < hands.size(); i++) {
//             if (getHandRank(hands.get(i)) > getHandRank(bestHands.get(0))) {
//                 bestHands.set(0, hands.get(i));
//             } else if (getHandRank(hands.get(i)) == getHandRank(bestHands.get(0))) {
//                 getHandRank(bestHands.get(0));
//                 List<Integer> firstHand = counts;

//                 getHandRank(hands.get(i));
//                 List<Integer> secondHand = counts;

//                 if (firstHand.equals(secondHand)) {
//                     bestHands.add(hands.get(i));
//                 } else {
//                     for (int j = 4; j >= 2; j--) {
//                         if (firstHand.contains(j) && secondHand.contains(j)) {
//                             if (firstHand.lastIndexOf(j) < secondHand.lastIndexOf(j)) {
//                                 bestHands.set(0, hands.get(i));
//                                 break;
//                             } else if (firstHand.lastIndexOf(j) > secondHand.lastIndexOf(j)) {
//                                 break;
//                             } else if (firstHand.lastIndexOf(j) == secondHand.lastIndexOf(j) && j == 2) {
//                                 if (firstHand.indexOf(j) < secondHand.indexOf(j)) {
//                                     bestHands.set(0, hands.get(i));
//                                 }
//                             }
//                         }
//                     }
//                     for (int k = firstHand.size() - 1; k >= 0; k--) {
//                         if (firstHand.get(k) <= 1 && secondHand.get(k) <= 1) {
//                             if (firstHand.get(k) < secondHand.get(k)) {
//                                 bestHands.set(0, hands.get(i));
//                                 break;
//                             } else if (firstHand.get(k) > secondHand.get(k)) {
//                                 break;
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         return bestHands;
//     }

//     public int getHandRank(String hand) {
//         String[] cards = hand.split(" ");
//         List<String> values = new ArrayList<>();
//         List<String> suits = new ArrayList<>();
//         for (String card : cards) {
//             if (card.length() == 2) {
//                 values.add(card.substring(0, 1));
//                 suits.add(card.substring(1));
//             } else {
//                 values.add(card.substring(0, 2));
//                 suits.add(card.substring(2));
//             }
//         }

//         for (int i = 0; i < values.size(); i++) {
//             switch (values.get(i)) {
//                 case "J" -> values.set(i, "11");
//                 case "Q" -> values.set(i, "12");
//                 case "K" -> values.set(i, "13");
//                 case "A" -> {
//                     if (values.contains("2") && values.contains("3") && values.contains("4") && values.contains("5")) {
//                         values.set(i, "1");
//                     } else {
//                         values.set(i, "14");
//                     }
//                 }
//             }
//         }

//         numericalValues = new ArrayList<>();
//         for (String value : values) {
//             numericalValues.add(Integer.valueOf(value));
//         }
//         Collections.sort(numericalValues);

//         List<Integer> possibleValues = new ArrayList<>();
//         counts = new ArrayList<>();
//         for (int i = 1; i <= 14; i++) {
//             counts.add(i - 1, countOccurrences(i));
//             possibleValues.add(i);
//         }

//         boolean isStraight = Collections.indexOfSubList(possibleValues, numericalValues) != -1;

//         boolean isFlush = suits.stream().distinct().count() == 1;

//         if (isStraight & isFlush) {
//             return 8; // straight flush
//         } else if (counts.contains(4)) {
//             return 7; // four of a kind
//         } else if (counts.contains(3) && counts.contains(2)) {
//             return 6; // full house
//         } else if (isFlush) {
//             return 5; // flush
//         } else if (isStraight) {
//             return 4; // straight
//         } else if (counts.contains(3)) {
//             return 3; // three of a kind
//         } else if (counts.stream().filter(value -> value == 2).count() == 2) {
//             return 2; // two pair
//         } else if (counts.contains(2)) {
//             return 1; // pair
//         } else {
//             return 0; // high card
//         }
//     }

//     public int countOccurrences(int valueToFind) {
//         return (int) numericalValues.stream().filter(value -> value.equals(valueToFind)).count();
//     }
// }

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Poker {

    private final List<String> hands;
    private final int N = 5;
    private final Map<Character, Integer> rankValues = new HashMap<>();

    {
        IntStream.rangeClosed(2, 9).forEach(i -> rankValues.put((char) ('0' + i), i));

        rankValues.put('T', 10);
        rankValues.put('J', 11);
        rankValues.put('Q', 12);
        rankValues.put('K', 13);
        rankValues.put('A', 14);
    }

    public Poker(List<String> hands) {
        this.hands = hands;
    }

    public List<String> getBestHands() {
        int maxHandValue = 0;

        for (String hand : hands) {
            int currentHandValue = getValue(hand);
            maxHandValue = Math.max(currentHandValue, maxHandValue);
        }

        int finalMaxHandValue = maxHandValue;

        return hands.stream()
                .filter(hand -> getValue(hand) == finalMaxHandValue)
                .collect(Collectors.toList());
    }

    private int getValue(String hand) {
        var cards = Arrays.stream(hand.split(" "))
                .map(c -> c.replace("10", "T"))
                .sorted(Comparator.comparingInt(c -> rankValues.get(c.charAt(0))))
                .collect(Collectors.toList());

        var groupingBy = cards.stream()
                .map(c -> c.charAt(0))
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        boolean isOnePair = groupingBy.containsValue(2L);
        boolean isTwoPair = groupingBy.entrySet().stream().filter(e -> e.getValue() == 2L).count() == 2;
        boolean isThreeOfAKind = groupingBy.containsValue(3L);
        boolean isStraight = checkStraight(cards);
        boolean isFlush = checkFlush(cards);
        boolean isFullHouse = groupingBy.containsValue(2L) && groupingBy.containsValue(3L);
        boolean isFourOfAKind = groupingBy.containsValue(4L);
        boolean isStraightFlush = isStraight && isFlush;
        boolean isFiveOfAKind = groupingBy.getOrDefault('A', 0L) == 4 && groupingBy.containsKey('J')
                || groupingBy.containsValue(5L);

        if (isFiveOfAKind) {
            return 900 + highCard(cards);
        }

        if (isStraightFlush) {
            return 800 + highCard(cards);
        }

        if (isFourOfAKind) {
            var fourOfAKind = groupingBy.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 4)
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(' ');

            return 700 + highCard(cards) + 4 * rankValues.get(fourOfAKind);
        }

        if (isFullHouse) {
            var threeOfAKind = groupingBy.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 3)
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(' ');

            var highPair = groupingBy.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 2)
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(' ');

            return 600 + 3 * rankValues.get(threeOfAKind) + 2 * rankValues.get(highPair);
        }

        if (isFlush) {
            return 500 + highCard(cards);
        }

        if (isStraight) {
            int lastCardIndex = (cards.get(N - 1).charAt(0) == 'A') ? N - 2 : N - 1;

            return 400 + rankValues.get(cards.get(lastCardIndex).charAt(0));
        }

        if (isThreeOfAKind) {
            var threeOfAKind = groupingBy.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 3)
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(' ');

            var cardsExcludingThree = cards.stream()
                    .filter(Predicate.not(c -> c.charAt(0) == threeOfAKind))
                    .collect(Collectors.toList());

            return 300 + highCard(cardsExcludingThree) + rankValues.get(threeOfAKind);
        }

        if (isTwoPair) {
            var highPair = groupingBy.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 2)
                    .map(Map.Entry::getKey)
                    .max(Comparator.comparingInt(rankValues::get)).orElse(' ');

            return 200 + 2 * rankValues.get(highPair) + lowCard(cards);
        }

        if (isOnePair) {
            return 100 + groupingBy.entrySet().stream()
                    .filter(e -> e.getValue() == 2)
                    .mapToInt(e -> rankValues.get(e.getKey()))
                    .sum();
        }

        return highCard(cards) + lowCard(cards);
    }

    private int lowCard(List<String> cards) {
        return rankValues.get(cards.get(0).charAt(0));
    }

    private boolean checkFlush(List<String> cards) {
        return cards.stream().allMatch(c -> cards.get(0).charAt(1) == c.charAt(1));
    }

    private int highCard(List<String> cards) {
        return rankValues.get(cards.get(cards.size() - 1).charAt(0));
    }

    private boolean checkStraight(List<String> cards) {
        int count = 0;

        if (cards.get(N - 1).charAt(0) == 'A' && cards.get(0).charAt(0) == '2') {
            var c = cards.get(N - 1);
            cards.remove(c);
            cards.add(0, c);
        }

        for (int i = 1; i < N; i++) {
            var prevRank = cards.get(i - 1).charAt(0);
            var currRank = cards.get(i).charAt(0);
            int previous = 1;

            if (prevRank != 'A' && currRank != '2')
                previous = rankValues.get(prevRank);
            var current = rankValues.get(currRank);

            if (current - previous == 1)
                count++;
            else
                break;
        }
        return count == 4;
    }

}