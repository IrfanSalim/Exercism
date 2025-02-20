import java.util.*;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        Set<String> newCollectionCard = new HashSet<>(cards);
        return newCollectionCard;
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);

    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        Set<String> myCollectionCopy = new HashSet<>(myCollection);
        Set<String> theirCollectionCopy = new HashSet<>(theirCollection);
        myCollectionCopy.removeAll(theirCollection);
        theirCollectionCopy.removeAll(myCollection);
        return !myCollectionCopy.isEmpty() && !theirCollectionCopy.isEmpty();
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        Set<String> common = new HashSet<>(collections.get(0));
        for (Set<String> collection : collections) {
            common.retainAll(collection);
        }

        return common;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> allCards = new HashSet<>();
        for (Set<String> collection : collections) {
            allCards.addAll(collection);
        }
        return allCards;
    }
}