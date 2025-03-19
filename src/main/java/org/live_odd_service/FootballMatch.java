package org.live_odd_service;

public class FootballMatch {
    private final String homeTeamName;
    private final String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    public FootballMatch(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public String getHomeTeamName() {
        return this.homeTeamName;
    }

    public String getAwayTeamName() {
        return this.awayTeamName;
    }

    public int getHomeTeamScore() {
        return this.homeTeamScore;
    }

    public int getAwayTeamScore() {
        return this.awayTeamScore;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public String toString() {
        return homeTeamName + " " + homeTeamScore + " - " + awayTeamScore + " " + awayTeamName;
    }
}
