package org.live_odd_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LiveOddServiceTest {
    @Test
    void testStartFootballMatch() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Croatia", "France");

        int initialScore = 0;
        Assertions.assertEquals("Croatia", match.getHomeTeamName(), "Home team name is not correct.");
        Assertions.assertEquals("France", match.getAwayTeamName(), "Away team name is not correct.");
        Assertions.assertEquals(initialScore, match.getHomeTeamScore(), "Home team initial score should be 0.");
        Assertions.assertEquals(initialScore, match.getAwayTeamScore(), "Away team initial score should be 0.");
    }

    @Test
    void testStartFootballMatchAlreadyStartedMatch() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();

        FootballMatch match = liveScoreboard.startFootballMatch("Mexico", "Canada");

        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName()));

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Mexico", "Canada"));
    }

    @Test
    void testStartFootballMatchWithValidTeams() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Croatia", "France");
        FootballMatch match2 = liveScoreboard.startFootballMatch("Poland", "Hungary");

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.areTeamPairsValid(match2.getHomeTeamName(), match2.getAwayTeamName()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.isTeamParticipatingWorldCup(match.getHomeTeamName(), true));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.isTeamParticipatingWorldCup(match2.getHomeTeamName(), true));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.isTeamParticipatingWorldCup(match2.getAwayTeamName(), true));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("", ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("", "Brazil"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("France", ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("   ", "   "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Spain", "   "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Sp ain", "   "));
        Assertions.assertFalse(liveScoreboard.isTeamParticipatingWorldCup(match.getHomeTeamName(),false));
        Assertions.assertTrue(liveScoreboard.isTeamParticipatingWorldCup(match.getAwayTeamName(),false));
    }

    @Test
    void testAreTeamPairsValid() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Croatia", "France");
        FootballMatch match2 = liveScoreboard.startFootballMatch("Mexico", "Canada");

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName()));
        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match2.getHomeTeamName(), match2.getAwayTeamName()));
    }

    @Test
    void testStartFootballMatchWithSameNames() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Croatia", "Croatia"));
    }

    @Test
    void testUpdateFootballMatchScore() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Spain", "Brazil");

        liveScoreboard.updateFootballMatchScore(match, 1, 2);

        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName()));
        Assertions.assertEquals(1, match.getHomeTeamScore(), "Home team score should be 1");
        Assertions.assertEquals(2, match.getAwayTeamScore(), "Away team score should be 2");
    }

    @Test
    void testUpdateNotExistingMatch() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch notExistingMatch = new FootballMatch("Uruguay", "England", 0,0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.updateFootballMatchScore(notExistingMatch, 1,1));
    }

    @Test
    void testUpdateFootballMatchWithNegativeScores() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Germany", "France");

        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.updateFootballMatchScore(match, -2, 1));
    }

    @Test
    void testUpdateFootballMatchScoreWhenMatchFinished() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Spain", "Brazil");

        liveScoreboard.updateFootballMatchScore(match, 1, 2);
        liveScoreboard.finishFootballMatch(match);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.updateFootballMatchScore(match, -2, 1));

    }
    @Test
    void testFinishFootballMatch() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Uruguay", "Italy");

        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName()));
        liveScoreboard.finishFootballMatch(match);

        Assertions.assertFalse(liveScoreboard.getSummary().contains(match));
    }

    @Test
    void testFinishNotExistingFootballMatch() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = new FootballMatch("Italy", "Spain",0,0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.finishFootballMatch(match));
    }

    @Test
    void testAlreadyFinishedMatch() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Spain", "Brazil");
        liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName());
        liveScoreboard.finishFootballMatch(match);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.finishFootballMatch(match));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.finishFootballMatch(null));
    }

    @Test
    void testGetFootballMatchesSummary() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();

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

        List<FootballMatch> correctScoreboardOrder = List.of(match4, match2, match1, match5, match3);

        List<FootballMatch> currentScoreboardOrder = liveScoreboard.getSummary();

        Assertions.assertEquals(correctScoreboardOrder, currentScoreboardOrder, "Displayed order of football matches is not correct");
    }

    @Test
    void testGetFootballMatchSummaryWithEmptyScoreboard() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        List<FootballMatch> correctScoreboardOrder = new ArrayList<>();

        List<FootballMatch> currentScoreboardOrder = liveScoreboard.getSummary();
        Assertions.assertEquals(correctScoreboardOrder, currentScoreboardOrder, "Displayed order of football matches is not correct");
    }
}
