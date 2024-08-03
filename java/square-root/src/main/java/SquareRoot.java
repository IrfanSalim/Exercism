public class SquareRoot {
    public int squareRoot(int radicand) {
      int i = 1;
      while (i * i <= radicand) {
        i++;
      }
      return i - 1;
    }
}

import java.util.stream.IntStream;

public class SquareRoot
{
    public int squareRoot(int radicand)
    {
        return IntStream.range(1, radicand + 1)
                .filter(i -> i * i == radicand)
                .findFirst()
                .orElse(0);
    }
}
