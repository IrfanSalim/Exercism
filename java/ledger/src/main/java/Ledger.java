// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;

// public class Ledger {
//     public LedgerEntry createLedgerEntry(String d, String desc, int c) {
//         LedgerEntry le = new LedgerEntry();
//         le.setChange(c);
//         le.setDescription(desc);
//         le.setLocalDate(LocalDate.parse(d));
//         return le;
//     }

//     public String format(String cur, String loc, LedgerEntry[] entries) {
//         String s = null;
//         String header = null;
//         String curSymb = null;
//         String datPat = null;
//         String decSep = null;
//         String thSep = null;
//         if (!cur.equals("USD") && !cur.equals("EUR")) {
//             throw new IllegalArgumentException("Invalid currency");
//         } else if (!loc.equals("en-US") && !loc.equals("nl-NL")) {
//             throw new IllegalArgumentException("Invalid locale");
//         } else {
//             if (cur.equals("USD")) {
//                 if (loc.equals("en-US")) {
//                     // bien
//                     curSymb = "$";
//                     datPat = "MM/dd/yyyy";
//                     decSep = ".";
//                     thSep = ",";
//                     header = "Date       | Description               | Change       ";
//                 } else if (loc.equals("nl-NL")) {
//                     // bien
//                     curSymb = "$";
//                     datPat = "dd/MM/yyyy";
//                     decSep = ",";
//                     thSep = ".";
//                     header = "Datum      | Omschrijving              | Verandering  ";
//                 }
//             } else if (cur.equals("EUR")) {
//                 if (loc.equals("en-US")) {
//                     curSymb = "€";
//                     datPat = "MM/dd/yyyy";
//                     decSep = ".";
//                     thSep = ",";
//                     header = "Date       | Description               | Change       ";
//                 } else if (loc.equals("nl-NL")) {
//                     curSymb = "€";
//                     datPat = "dd/MM/yyyy";
//                     decSep = ",";
//                     thSep = ".";
//                     header = "Datum      | Omschrijving              | Verandering  ";
//                 }
//             }
//         }
//         s = header;
//         if (entries.length > 0) {
//             List<LedgerEntry> neg = new ArrayList<>();
//             List<LedgerEntry> pos = new ArrayList<>();
//             for (int i = 0; i < entries.length; i++) {
//                 LedgerEntry e = entries[i];
//                 if (e.getChange() >= 0) {
//                     pos.add(e);
//                 } else {
//                     neg.add(e);
//                 }
//             }
//             neg.sort((o1, o2) -> o1.getLocalDate().compareTo(o2.getLocalDate()));
//             pos.sort((o1, o2) -> o1.getLocalDate().compareTo(o2.getLocalDate()));
//             List<LedgerEntry> all = new ArrayList<>();
//             all.addAll(neg);
//             all.addAll(pos);
//             for (int i = 0; i < all.size(); i++) {
//                 LedgerEntry e = all.get(i);
//                 String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(datPat));
//                 String desc = e.getDescription();
//                 if (desc.length() > 25) {
//                     desc = desc.substring(0, 22);
//                     desc = desc + "...";
//                 }
//                 String converted = null;
//                 if (e.getChange() < 0) {
//                     converted = String.format("%.02f", (e.getChange() / 100) * -1);
//                 } else {
//                     converted = String.format("%.02f", e.getChange() / 100);
//                 }
//                 String[] parts = converted.split("\\.");
//                 String amount = "";
//                 int count = 1;
//                 for (int ind = parts[0].length() - 1; ind >= 0; ind--) {
//                     if (((count % 3) == 0) && ind > 0) {
//                         amount = thSep + parts[0].charAt(ind) + amount;
//                     } else {
//                         amount = parts[0].charAt(ind) + amount;
//                     }
//                     count++;
//                 }
//                 if (loc.equals("nl-NL")) {
//                     amount = curSymb + " " + amount + decSep + parts[1];
//                 } else {
//                     amount = curSymb + amount + decSep + parts[1];
//                 }

//                 if (e.getChange() < 0 && loc.equals("en-US")) {
//                     amount = "(" + amount + ")";
//                 } else if (e.getChange() < 0 && loc.equals("nl-NL")) {
//                     amount = curSymb + " -" + amount.replace(curSymb, "").trim() + " ";
//                 } else if (loc.equals("nl-NL")) {
//                     amount = " " + amount + " ";
//                 } else {
//                     amount = amount + " ";
//                 }

//                 s = s + "\n";
//                 s = s + String.format("%s | %-25s | %13s",
//                         date,
//                         desc,
//                         amount);
//             }
//         }
//         return s;
//     }

//     public static class LedgerEntry {
//         LocalDate localDate;
//         String description;
//         double change;

//         public LocalDate getLocalDate() {
//             return localDate;
//         }

//         public void setLocalDate(LocalDate localDate) {
//             this.localDate = localDate;
//         }

//         public String getDescription() {
//             return description;
//         }

//         public void setDescription(String description) {
//             this.description = description;
//         }

//         public double getChange() {
//             return change;
//         }

//         public void setChange(double change) {
//             this.change = change;
//         }
//     }
// }

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Ledger {
    public LedgerEntry createLedgerEntry(String date, String description, int change) {
        LedgerEntry lEntry = new LedgerEntry();
        lEntry.setChange(change);
        lEntry.setDescription(description);
        lEntry.setLocalDate(LocalDate.parse(date));
        return lEntry;
    }

    public String format(String cur, String loc, LedgerEntry[] entries) {

        if (!cur.equals("USD") && !cur.equals("EUR") || !loc.equals("en-US") && !loc.equals("nl-NL")) {
            throw new IllegalArgumentException("Invalid currency");
        }
        String curSymb = cur.equals("USD") ? "$" : "€";
        String datPat = loc.equals("en-US") ? "MM/dd/yyyy" : "dd/MM/yyyy";
        String decSep = loc.equals("en-US") ? "." : ",";
        String thSep = loc.equals("en-US") ? "," : ".";
        String header = loc.equals("en-US") ? "Date       | Description               | Change       "
                : "Datum      | Omschrijving              | Verandering  ";
        StringBuilder s = new StringBuilder("");
        s.append(header);

        Map<Boolean, List<LedgerEntry>> partitionedEntries = Arrays.stream(entries)
                .collect(Collectors.partitioningBy(e -> e.getChange() >= 0));
        List<LedgerEntry> all = partitionedEntries.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .sorted(Comparator.comparing(LedgerEntry::getLocalDate))
                .collect(Collectors.toList());

        String formattedEntries = all.stream()
                .map(e -> {
                    String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(datPat));
                    String desc = e.getDescription().length() > 25 ? e.getDescription().substring(0, 22) + "..."
                            : e.getDescription();
                    String converted = String.format("%.02f", Math.abs(e.getChange()) / 100);
                    String[] parts = converted.split("\\.");
                    String amount = getString(parts, thSep);
                    amount = loc.equals("nl-NL") ? curSymb + " " + amount + decSep + parts[1] + " "
                            : curSymb + amount + decSep + parts[1];
                    amount = e.getChange() < 0 ? (loc.equals("en-US") ? "(" + amount + ")"
                            : curSymb + " -" + amount.replace(curSymb + " ", "")) : amount.trim() + " ";
                    return String.format("%s | %-25s | %13s", date, desc, amount);
                })
                .collect(Collectors.joining("\n"));
        return formattedEntries.length() > 0 ? s.append("\n" + formattedEntries).toString() : s.toString();
    }

    private static String getString(String[] parts, String thSep) {
        StringBuilder amountBuilder = new StringBuilder();
        int count = 1;
        for (int ind = parts[0].length() - 1; ind >= 0; ind--) {
            if (((count % 3) == 0) && ind > 0) {
                amountBuilder.insert(0, thSep + parts[0].charAt(ind));
            } else {
                amountBuilder.insert(0, parts[0].charAt(ind));
            }
            count++;
        }
        return amountBuilder.toString();
    }

    public static class LedgerEntry {
        LocalDate localDate;
        String description;
        double change;

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }
    }
}