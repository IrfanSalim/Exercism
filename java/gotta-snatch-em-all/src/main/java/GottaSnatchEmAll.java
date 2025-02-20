// import java.util.*;

// class GottaSnatchEmAll {

//     static Set<String> newCollection(List<String> cards) {
//         Set<String> newCollectionCard = new HashSet<>(cards);
//         return newCollectionCard;
//     }

//     static boolean addCard(String card, Set<String> collection) {
//         return collection.add(card);

//     }

//     static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
//         Set<String> myCollectionCopy = new HashSet<>(myCollection);
//         Set<String> theirCollectionCopy = new HashSet<>(theirCollection);
//         myCollectionCopy.removeAll(theirCollection);
//         theirCollectionCopy.removeAll(myCollection);
//         return !myCollectionCopy.isEmpty() && !theirCollectionCopy.isEmpty();
//     }

//     static Set<String> commonCards(List<Set<String>> collections) {
//         Set<String> common = new HashSet<>(collections.get(0));
//         for (Set<String> collection : collections) {
//             common.retainAll(collection);
//         }

//         return common;
//     }

//     static Set<String> allCards(List<Set<String>> collections) {
//         Set<String> allCards = new HashSet<>();
//         for (Set<String> collection : collections) {
//             allCards.addAll(collection);
//         }
//         return allCards;
//     }
// }

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        return !myCollection.containsAll(theirCollection) && !theirCollection.containsAll(myCollection);
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        return collections.stream().reduce((a, c) -> {
            Set<String> n = new HashSet<>(a);
            n.retainAll(c);
            return n;
        }).orElse(Set.of());
    }

    static Set<String> allCards(List<Set<String>> collections) {
        return collections.stream().reduce((a, c) -> {
            Set<String> n = new HashSet<>(a);
            n.addAll(c);
            return n;
        }).orElse(Set.of());
    }
}