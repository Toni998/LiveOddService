package org.live_odd_service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LiveScoreboard {

    List<String> worldCupTeams = List.of("Mexico", "Canada", "Spain", "Brazil", "Germany", "France", "Uruguay", "Italy", "Argentina", "Australia");
    List<List<String>> worldCupTeamPairs =  new ArrayList<>();

    public List<List<String>> initWorldCupPairs() {
        return List.of(
                List.of("Mexico", "Canada"),
                List.of("Spain", "Brazil"),
                List.of("Germany", "France"),
                List.of("Uruguay", "Italy"),
                List.of("Argentina", "Australia")
        );
    }

    List<FootballMatch> footballMatches = new ArrayList<>();

    FootballMatch startFootballMatch(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || awayTeamName == null || homeTeamName.isBlank() || awayTeamName.isBlank() || homeTeamName.equals(awayTeamName)) {
            throw new IllegalArgumentException("Incorrect team names");
        }
        if (footballMatches.stream().anyMatch(match ->
                match.getHomeTeamName().equals(homeTeamName) && match.getAwayTeamName().equals(awayTeamName))) {
            throw new IllegalArgumentException("Match already started!");
        }

        FootballMatch match = new FootballMatch(homeTeamName, awayTeamName, 0,0);
        footballMatches.add(match);

        return match;
    }

    public void updateFootballMatchScore(FootballMatch match, int homeTeamScore, int awayTeamScore) {
        if (!footballMatches.contains(match)) {
            throw new IllegalArgumentException("Match update not possible, invalid match");
        }

        if (homeTeamScore < -1 || awayTeamScore < -1) {
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

    public boolean isTeamParticipatingWorldCup(String teamName, boolean throwException) {
        boolean isParticipating = worldCupTeams.contains(teamName);
        if (!isParticipating && throwException) {
            throw new IllegalArgumentException("Team is not participating world cup!");
        }
        return isParticipating;
    }

    public boolean areTeamPairsValid(String homeTeamName, String awayTeamName, boolean throwException) {
        worldCupTeamPairs = initWorldCupPairs();
        boolean isPairValid = worldCupTeamPairs.contains(List.of(homeTeamName, awayTeamName));
        if (!isPairValid && throwException) {
            throw new IllegalArgumentException("Team pair is not valid");
        }
        return isPairValid;
    }
}
