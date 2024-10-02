import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class School {
    private final Map<String, Integer> roster = new TreeMap<>();

    public boolean add(String student, int grade) {
        int auxLen = this.roster.size();

        roster.putIfAbsent(student, grade);

        return roster.size() > auxLen;
    }

    public List<String> roster() {
        return roster.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<String> grade(int grade) {
        return roster.entrySet().stream()
                .filter(entry -> entry.getValue() == grade)
                .map(Map.Entry::getKey)
                .toList();
    }

}

import java.util.*;
import java.util.stream.Collectors;

public class School {

    private final SortedMap<Integer, SortedSet<String>> roster = new TreeMap<>();

    public void add(String name, int grade) {
        roster.computeIfAbsent(grade, i -> new TreeSet<>()).add(name);
    }

    public List<String> roster() {
        return roster.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<String> grade(int grade) {
        return new ArrayList<>(roster.getOrDefault(grade, Collections.emptySortedSet()));
    }
}