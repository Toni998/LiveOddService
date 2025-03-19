package org.live_odd_service;

import java.util.ArrayList;
import java.util.List;

public class LiveScoreboard {

    List<FootballMatch> footballMatches = new ArrayList<>();

    FootballMatch startFootballMatch(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || awayTeamName == null || homeTeamName.isBlank() || awayTeamName.isBlank() || homeTeamName.equals(awayTeamName)) {
            throw new IllegalArgumentException("Incorrect team names");
        }
        FootballMatch match = new FootballMatch(homeTeamName, awayTeamName, 0,0);
        footballMatches.add(match);

        return match;
    }

    public void updateFootballMatchScore(FootballMatch match, int homeTeamScore, int awayTeamScore) {
        if (!footballMatches.contains(match)) {
            throw new IllegalArgumentException("Match doesn't exists");
        }
        match.updateScore(homeTeamScore, awayTeamScore);
    }
}
