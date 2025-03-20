package org.live_odd_service;

import java.util.List;

public class WorldCup {
    private final List<String> worldCupTeams;
    private final List<List<String>> worldCupTeamPairs;

    public WorldCup() {
        this.worldCupTeams = List.of(
                "Mexico", "Canada", "Spain", "Brazil",
                "Germany", "France", "Uruguay", "Italy",
                "Argentina", "Australia"
        );
        this.worldCupTeamPairs = List.of(
                List.of("Mexico", "Canada"),
                List.of("Spain", "Brazil"),
                List.of("Germany", "France"),
                List.of("Uruguay", "Italy"),
                List.of("Argentina", "Australia")
        );
    }

    public boolean isTeamParticipating(String teamName) {
        return worldCupTeams.contains(teamName);
    }

    public boolean isValidTeamPair(String homeTeam, String awayTeam) {
        return worldCupTeamPairs.contains(List.of(homeTeam, awayTeam));
    }
}
