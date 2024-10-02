import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class School {
    private final HashMap<Integer, List<String>> stuMap = new HashMap<>();
    private final HashSet<String> allStudents = new HashSet<>();

    public synchronized boolean add(String student, int grade) {
        if (allStudents.contains(student)) {
            return false;
        }
        allStudents.add(student);
        stuMap.computeIfAbsent(grade, k -> new ArrayList<>()).add(student);
        return true;
    }

    public synchronized List<String> roster() {
        List<String> sortedRoster = new ArrayList<>(allStudents);
        Collections.sort(sortedRoster);
        return sortedRoster;
    }

    public synchronized List<String> grade(int grade) {
        List<String> studentsInGrade = stuMap.getOrDefault(grade, new ArrayList<>());
        Collections.sort(studentsInGrade);
        return studentsInGrade;
    }

}
