class TwelveDays {
      
      String[] verseList;
      
      public TwelveDays() {
        verseList = new String[12]; 

        verseList[0] = "On the first day of Christmas my true love gave to me: a Partridge in a Pear Tree.";
        verseList[1] = "On the second day of Christmas my true love gave to me: two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[2] = "On the third day of Christmas my true love gave to me: three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[3] = "On the fourth day of Christmas my true love gave to me: four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[4] = "On the fifth day of Christmas my true love gave to me: five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[5] = "On the sixth day of Christmas my true love gave to me: six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[6] = "On the seventh day of Christmas my true love gave to me: seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[7] = "On the eighth day of Christmas my true love gave to me: eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[8] = "On the ninth day of Christmas my true love gave to me: nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[9] = "On the tenth day of Christmas my true love gave to me: ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[10] = "On the eleventh day of Christmas my true love gave to me: eleven Pipers Piping, ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
        verseList[11] = "On the twelfth day of Christmas my true love gave to me: twelve Drummers Drumming, eleven Pipers Piping, ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.";
      }

     String verse(int verseNumber) {
      return verseList[verseNumber - 1] + "\n";
    }

    String verses(int startVerse, int endVerse) {
      StringBuilder result = new StringBuilder();
      for (int i = startVerse - 1; i < endVerse; i++) {
        result.append(verseList[i] + "\n");
        if (i != endVerse-1) result.append("\n");
      }
      return result.toString();
    }
    
    String sing() {
      return verses(1, 12);
    }
}

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {

    private static final String[] ORDINAL = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};
    private static final String START = "On the %s day of Christmas my true love gave to me: ";
    private static final String[] PARTS = {"a Partridge in a Pear Tree", "two Turtle Doves", "three French Hens", "four Calling Birds", "five Gold Rings", "six Geese-a-Laying", "seven Swans-a-Swimming", "eight Maids-a-Milking", "nine Ladies Dancing", "ten Lords-a-Leaping", "eleven Pipers Piping", "twelve Drummers Drumming"};

    String verse(int verseNumber) {
        String verse;

        if (1 < verseNumber) {
            verse = IntStream.iterate(verseNumber - 1, e -> e - 1).limit(verseNumber - 1)
                    .mapToObj(i -> PARTS[i] + ", ")
                    .collect(Collectors.joining(""));
            verse = verse + "and " + PARTS[0];
        } else {
            verse = PARTS[0];
        }
        return String.format(START, ORDINAL[verseNumber - 1]) + verse + ".\n";
    }

    String verses(int startVerse, int endVerse) {
        String verses;

        verses = IntStream.rangeClosed(startVerse, endVerse)
                .mapToObj(i -> verse(i) + "\n")
                .collect(Collectors.joining(""));
        return verses.substring(0, verses.length() - 1);
    }

    String sing() {
        return verses(1, 12);
    }
}


