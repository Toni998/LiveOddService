package org.live_odd_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void testStartFootballMatchWithEmptyNames() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("", "Finland"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Poland", ""));
    }

    @Test
    void testStartFootballMatchWithSameNames() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Croatia", "Croatia"));
    }

    @Test
    void testUpdateFootballMatchScore() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();
        FootballMatch match = liveScoreboard.startFootballMatch("Spain", "Italy");

        liveScoreboard.updateFootballMatchScore(match, 1, 2);

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
        FootballMatch match = liveScoreboard.startFootballMatch("France", "Netherlands");

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.updateFootballMatchScore(match, -2, 1));
    }
}
