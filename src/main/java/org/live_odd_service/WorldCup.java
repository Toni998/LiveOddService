package org.live_odd_service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldCup {
    private final List<String> worldCupTeams;
    private final Map<String, String> worldCupTeamPairs;

    public WorldCup() {
        this.worldCupTeams = List.of(
                "Mexico", "Canada", "Spain", "Brazil",
                "Germany", "France", "Uruguay", "Italy",
                "Argentina", "Australia"
        );
        this.worldCupTeamPairs = new HashMap<>();
        worldCupTeamPairs.put("Mexico", "Canada");
        worldCupTeamPairs.put("Spain", "Brazil");
        worldCupTeamPairs.put("Germany", "France");
        worldCupTeamPairs.put("Uruguay", "Italy");
        worldCupTeamPairs.put("Argentina", "Australia");
    }

    public boolean isTeamParticipating(String teamName) {
        return worldCupTeams.contains(teamName);
    }

    public boolean isValidTeamPair(String homeTeam, String awayTeam) {
        return worldCupTeamPairs.containsKey(homeTeam) && worldCupTeamPairs.get(homeTeam).equals(awayTeam);
    }
}
