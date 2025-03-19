package org.live_odd_service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new IllegalArgumentException("Match update not possible, invalid match");
        }

        if (homeTeamScore == -1 && awayTeamScore >= 0 || homeTeamScore >=0 && awayTeamScore == -1) {
            if (match.getHomeTeamScore() + homeTeamScore < 0 || match.getAwayTeamScore() + awayTeamScore < 0) {
                throw new IllegalArgumentException("Invalid score update, score cannot be negative after update");
            }
            match.updateScore(match.getHomeTeamScore() + homeTeamScore, match.getAwayTeamScore() + awayTeamScore);
        } else if (homeTeamScore < -1 || awayTeamScore < -1) {
            throw new IllegalArgumentException("Invalid score update, score cannot be negative after update");
        } else {
            match.updateScore(homeTeamScore, awayTeamScore);
        }
    }

    public void finishFootballMatch(FootballMatch match) {
        if (!footballMatches.contains(match)) {
            throw new IllegalArgumentException("Invalid match");
        }
        footballMatches.remove(match);
    }

    public List<FootballMatch> getSummary() {
        return footballMatches.stream()
                .sorted(Comparator.comparingInt((FootballMatch match) -> match.getHomeTeamScore() + match.getAwayTeamScore())
                        .thenComparing(footballMatches::indexOf)
                        .reversed())
                .collect(Collectors.toList());
    }
}
