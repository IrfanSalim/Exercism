import java.util.List;
import java.util.ArrayList;

class Flattener {

    <T> List<T> flatten(List<T> list) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (t instanceof List) {
                result.addAll(flatten((List<T>) t));
            } else if (t != null) {
                result.add(t);
            }
        }
        return result;
    }

}
