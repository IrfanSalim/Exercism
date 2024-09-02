import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

class HighScores {
    List<Integer> highScores;
    
    public HighScores(List<Integer> highScores) {
      this.highScores = highScores;
    }

    List<Integer> scores() {
      return this.highScores;
    }

    Integer latest() {
      return highScores.get(highScores.size() - 1);
    }

    Integer personalBest() {
      return highScores.stream().max(Integer::compare).get();
    }

    List<Integer> personalTopThree() {
      return highScores.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
    }

}
