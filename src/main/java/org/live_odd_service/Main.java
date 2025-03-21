package org.live_odd_service;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LiveScoreboard liveScoreboard = SummaryFactory.createLiveScoreboard();

        initialFootballMatches(liveScoreboard);

        List<FootballMatch> currentScoreboardOrder = liveScoreboard.getSummary();

        System.out.println("Unordered scoreboard");
        for(FootballMatch footballMatch : liveScoreboard.getUnorderedSummary()) {
            System.out.println(footballMatch);
        }

        System.out.println("----------------------");
        System.out.println("Ordered scoreboard");
        for(FootballMatch footballMatch : currentScoreboardOrder) {
            System.out.println(footballMatch);
        }
    }

    private static void initialFootballMatches(LiveScoreboard liveScoreboard) {
        FootballMatch match1 = liveScoreboard.startFootballMatch("Mexico", "Canada");
        liveScoreboard.updateFootballMatchScore(match1, 0, 5);
        FootballMatch match2 = liveScoreboard.startFootballMatch("Spain", "Brazil");
        liveScoreboard.updateFootballMatchScore(match2, 10, 2);
        FootballMatch match3 = liveScoreboard.startFootballMatch("Germany", "France");
        liveScoreboard.updateFootballMatchScore(match3, 2, 2);
        FootballMatch match4 = liveScoreboard.startFootballMatch("Uruguay", "Italy");
        liveScoreboard.updateFootballMatchScore(match4, 6, 6);
        FootballMatch match5 = liveScoreboard.startFootballMatch("Argentina", "Australia");
        liveScoreboard.updateFootballMatchScore(match5, 3, 1);
    }
}
