// import java.util.List;
// import java.util.Arrays;
// import java.util.ArrayList;

// public class Allergies {

//     private final int score;

//     public Allergies(int score) {
//         this.score = score;
//     }

//     public boolean isAllergicTo(Allergen a) {
//         return getList().contains(a);
//     }

//     public List<Allergen> getList() {
//         List<Allergen> results = new ArrayList<Allergen>();

//         for (Allergen a : Allergen.values()) {
//             if ((score & a.getScore()) != 0) {
//                 results.add(a);
//             }
//         }

//         return results;
//     }

// }

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Allergies {
    private int score;

    public Allergies(int score) {
        this.score = score;
    }

    public boolean isAllergicTo(Allergen allergen) {
        return (score & allergen.getScore()) != 0;
    }

    public List<Allergen> getList() {
        return Arrays.stream(Allergen.values())
                .filter(this::isAllergicTo)
                .collect(Collectors.toList());
    }
}