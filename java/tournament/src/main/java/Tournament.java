// import java.util.*;

// class Team {

//     String name;
//     int win, loss, draw;

//     Team(String name, int win, int loss, int draw) {
//         this.name = name;
//         this.win = win;
//         this.loss = loss;
//         this.draw = draw;
//     }

//     void addResult(int result) {
//         if (result == 0)
//             win++;
//         else if (result == 1)
//             loss++;
//         else if (result == 2)
//             draw++;
//     }

//     int getMatches() {
//         return win + loss + draw;
//     }

//     int getPoints() {
//         return 3 * win + draw;
//     }
// }

// class Tournament {

//     private final Map<String, Team> results = new HashMap<>();

//     public void applyResults(String input) {
//         var matches = input.split("\n");
//         for (var match : matches) {
//             var result = match.split(";");
//             int x = 0, y = 1;
//             if (result[2].equals("loss")) {
//                 x = 1;
//                 y = 0;
//             } else if (result[2].equals("draw")) {
//                 x = 2;
//                 y = 2;
//             }
//             results.computeIfAbsent(result[0], k -> new Team(k, 0, 0, 0)).addResult(x);
//             results.computeIfAbsent(result[1], k -> new Team(k, 0, 0, 0)).addResult(y);
//         }
//     }

//     public String printTable() {
//         List<Team> teams = new ArrayList<>(results.values());
//         teams.sort((x, y) -> {
//             int xp = x.getPoints(), yp = y.getPoints();
//             return xp == yp ? x.name.compareTo(y.name) : yp - xp;
//         });
//         var sb = new StringBuffer("Team                           | MP |  W |  D |  L |  P\n");
//         for (var team : teams) {
//             sb.append(String.format("%-30s |%3d |%3d |%3d |%3d |%3d\n", team.name,
//                     team.getMatches(), team.win, team.draw, team.loss, team.getPoints()));
//         }
//         return sb.toString();
//     }
// }

import java.util.HashMap;
import java.util.Map;

class Tournament {
    private Map<String, int[]> table = new HashMap<>();

    void applyResults(String x) {
        for (String s : x.split("\n")) {
            String game[] = s.split(";");

            int a = game[2].equals("win") ? 1 : game[2].equals("loss") ? -1 : 0;
            table.putIfAbsent(game[0], new int[5]);
            table.putIfAbsent(game[1], new int[5]);

            update(game[0], a);
            update(game[1], -a);
        }
    }

    private void update(String s, int a) {
        int values[] = table.get(s);
        ++values[0];
        values[1] += a == 1 ? 1 : 0;
        values[2] += a == 0 ? 1 : 0;
        values[3] -= a == -1 ? -1 : 0;
        values[4] += 1 + (a == 1 ? 2 : a);
        table.put(s, values);
    }

    String printTable() {
        return table.entrySet().stream()
                .sorted(this::compare)
                .map(this::show)
                .reduce("Team                           | MP |  W |  D |  L |  P\n", String::concat);
    }

    private String show(Map.Entry<String, int[]> team) {
        StringBuilder res = new StringBuilder(String.format("%-30s", team.getKey()));
        for (int i : team.getValue())
            res.append(String.format(" | %2d", i));
        return res + "\n";
    }

    private int compare(Map.Entry<String, int[]> a, Map.Entry<String, int[]> b) {
        int c = Integer.compare(b.getValue()[4], a.getValue()[4]);
        return c == 0 ? a.getKey().compareTo(b.getKey()) : c;
    }

}