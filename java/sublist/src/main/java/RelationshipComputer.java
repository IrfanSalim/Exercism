import java.util.Collections;
import java.util.List;

public class RelationshipComputer<T> {

    public Relationship computeRelationship(List<T> a, List<T> b) {
        if (a.equals(b)) {
            return Relationship.EQUAL;
        }

        if (Collections.indexOfSubList(a, b) != -1) {
            return Relationship.SUPERLIST;
        }

        if (Collections.indexOfSubList(b, a) != -1) {
            return Relationship.SUBLIST;
        }

        return Relationship.UNEQUAL;
    }
}