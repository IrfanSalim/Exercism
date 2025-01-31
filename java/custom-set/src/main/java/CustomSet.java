import java.util.*;

public class CustomSet<T> {
    private List<T> set = new ArrayList<T>();
    private int size = 0;

    public CustomSet() {
    }

    public CustomSet(List<T> elements) {
        elements.forEach(this::add);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(T element) {
        return this.set.contains(element);
    }

    public boolean equals(CustomSet<T> other) {
        return this.size == other.size && this.isSubset(other);
    }

    public void add(T element) {
        if (!this.contains(element)) {
            this.size++;
            this.set.add(element);
        }
    }

    public boolean isSubset(CustomSet<T> other) {
        for (T element : other.set)
            if (!this.contains(element))
                return false;

        return true;
    }

    public boolean isDisjoint(CustomSet<T> other) {
        for (T element : this.set)
            if (other.contains(element))
                return false;
        return true;
    }

    public CustomSet<T> getIntersection(CustomSet<T> other) {
        CustomSet<T> intersection = new CustomSet<T>();
        for (T element : this.set)
            if (other.contains(element))
                intersection.add(element);
        return intersection;
    }

    public CustomSet<T> getDifference(CustomSet<T> other) {
        CustomSet<T> difference = new CustomSet<T>();
        for (T element : this.set)
            if (!other.contains(element))
                difference.add(element);
        return difference;
    }

    public CustomSet<T> getUnion(CustomSet<T> other) {
        CustomSet<T> union = new CustomSet<T>();
        this.set.forEach(union::add);
        other.set.forEach(union::add);

        return union;
    }
}