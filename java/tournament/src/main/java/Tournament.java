import java.util.*;

class Team {

    String name;
    int win, loss, draw;

    Team(String name, int win, int loss, int draw) {
        this.name = name;
        this.win = win;
        this.loss = loss;
        this.draw = draw;
    }

    void addResult(int result) {
        if (result == 0)
            win++;
        else if (result == 1)
            loss++;
        else if (result == 2)
            draw++;
    }

    int getMatches() {
        return win + loss + draw;
    }

    int getPoints() {
        return 3 * win + draw;
    }
}

class Tournament {

    private final Map<String, Team> results = new HashMap<>();

    public void applyResults(String input) {
        var matches = input.split("\n");
        for (var match : matches) {
            var result = match.split(";");
            int x = 0, y = 1;
            if (result[2].equals("loss")) {
                x = 1;
                y = 0;
            } else if (result[2].equals("draw")) {
                x = 2;
                y = 2;
            }
            results.computeIfAbsent(result[0], k -> new Team(k, 0, 0, 0)).addResult(x);
            results.computeIfAbsent(result[1], k -> new Team(k, 0, 0, 0)).addResult(y);
        }
    }

    public String printTable() {
        List<Team> teams = new ArrayList<>(results.values());
        teams.sort((x, y) -> {
            int xp = x.getPoints(), yp = y.getPoints();
            return xp == yp ? x.name.compareTo(y.name) : yp - xp;
        });
        var sb = new StringBuffer("Team                           | MP |  W |  D |  L |  P\n");
        for (var team : teams) {
            sb.append(String.format("%-30s |%3d |%3d |%3d |%3d |%3d\n", team.name,
                    team.getMatches(), team.win, team.draw, team.loss, team.getPoints()));
        }
        return sb.toString();
    }
}