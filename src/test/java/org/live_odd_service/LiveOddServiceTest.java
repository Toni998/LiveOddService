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

        Assertions.assertThrows(IllegalAccessError.class, () -> liveScoreboard.startFootballMatch("", "Finland"));
        Assertions.assertThrows(IllegalAccessError.class, () -> liveScoreboard.startFootballMatch("Poland", ""));
    }

    @Test
    void testStartFootballMatchWithSameNames() {
        LiveScoreboard liveScoreboard = new LiveScoreboard();

        Assertions.assertThrows(IllegalAccessError.class, () -> liveScoreboard.startFootballMatch("Croatia", "Croatia"));
    }
}
