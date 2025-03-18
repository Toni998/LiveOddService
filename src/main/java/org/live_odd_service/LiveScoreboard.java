package org.live_odd_service;

public class LiveScoreboard {
    FootballMatch startFootballMatch(String homeTeamName, String awayTeamName) {
        return new FootballMatch(homeTeamName, awayTeamName, 0,0);
    }
}
