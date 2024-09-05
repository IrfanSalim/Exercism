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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Flattener {
    public static List<Object> flatten(List<Object> input) {
        return input
                .stream()
                .flatMap(e -> e instanceof List ?
                        Flattener.flatten((List<Object>) e).stream() :
                        Stream.of(e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
