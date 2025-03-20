package org.live_odd_service;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LiveOddServiceTest {

    WorldCup worldCup;
    LiveScoreboard liveScoreboard;
    @BeforeEach
    void setUp() {
        worldCup = new WorldCup();
        liveScoreboard = new LiveScoreboard(worldCup);
    }

    @Test
    @DisplayName("Participate world cup")
    @Order(1)
    void testParticipateWorldCup() {
        FootballMatch match = liveScoreboard.startFootballMatch("Mexico", "Canada");

        Assertions.assertFalse(liveScoreboard.isTeamParticipatingWorldCup("Croatia",false));
        Assertions.assertTrue(liveScoreboard.isTeamParticipatingWorldCup("France",false));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.areTeamPairsValid("Croatia", "France",true));
        Assertions.assertTrue(liveScoreboard.areTeamPairsValid("Mexico", "Canada",false));
        Assertions.assertTrue(liveScoreboard.isTeamParticipatingWorldCup("Mexico",false));
    }

    @Test
    @DisplayName("Start football match")
    @Order(2)
    void testStartFootballMatch() {
        FootballMatch match = liveScoreboard.startFootballMatch("Germany", "France");

        int initialScore = 0;
        Assertions.assertEquals("Germany", match.getHomeTeamName(), "Home team name is not correct.");
        Assertions.assertEquals("France", match.getAwayTeamName(), "Away team name is not correct.");
        Assertions.assertEquals(initialScore, match.getHomeTeamScore(), "Home team initial score should be 0.");
        Assertions.assertEquals(initialScore, match.getAwayTeamScore(), "Away team initial score should be 0.");
    }

    @Test
    @DisplayName("Start football match - already started match")
    @Order(3)
    void testStartFootballMatchAlreadyStartedMatch() {
        FootballMatch match = liveScoreboard.startFootballMatch("Mexico", "Canada");
        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName(), false));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Mexico", "Canada"));
    }

    @Test
    @DisplayName("Start football match - valid names")
    @Order(4)
    void testStartFootballMatchWithValidTeams() {
        FootballMatch match = liveScoreboard.startFootballMatch("Mexico", "Canada");

        Assertions.assertTrue(liveScoreboard.isTeamParticipatingWorldCup(match.getAwayTeamName(),false));
        Assertions.assertTrue(liveScoreboard.isTeamParticipatingWorldCup(match.getHomeTeamName(),false));
        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(),match.getAwayTeamName(),false));
    }

    @Test
    @DisplayName("Start football match - invalid names")
    @Order(5)
    void testStartFootballMatchWithInvalidTeams() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("", ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Croatia", "Brazil"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("France", ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("   ", "   "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Spain", "   "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Sp ain", "   "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch("Croatia", "France"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.startFootballMatch(null, null));
        Assertions.assertFalse(liveScoreboard.areTeamPairsValid("Croatia","Brazil",false));
        Assertions.assertFalse(liveScoreboard.isTeamParticipatingWorldCup("Croatia",false));
    }

    @Test
    @DisplayName("Update match score")
    @Order(6)
    void testUpdateFootballMatchScore() {
        FootballMatch match = liveScoreboard.startFootballMatch("Spain", "Brazil");

        liveScoreboard.updateFootballMatchScore(match, 1, 2);

        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName(), false));
        Assertions.assertEquals(1, match.getHomeTeamScore(), "Home team score should be 1");
        Assertions.assertEquals(2, match.getAwayTeamScore(), "Away team score should be 2");
    }

    @Test
    @DisplayName("Update match score - not existing match")
    @Order(7)
    void testUpdateNotExistingMatch() {
        FootballMatch notExistingMatch = new FootballMatch("Uruguay", "England", 0,0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.updateFootballMatchScore(notExistingMatch, 1,1));
    }

    @Test
    @DisplayName("Update match score - negative score")
    @Order(8)
    void testUpdateFootballMatchWithNegativeScores() {
        FootballMatch match = liveScoreboard.startFootballMatch("Germany", "France");

        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName(), false));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.updateFootballMatchScore(match, -2, 1));
    }

    @Test
    @DisplayName("Update match score - finished match")
    @Order(9)
    void testUpdateFootballMatchScoreWhenMatchFinished() {
        FootballMatch match = liveScoreboard.startFootballMatch("Spain", "Brazil");

        liveScoreboard.updateFootballMatchScore(match, 1, 2);
        liveScoreboard.finishFootballMatch(match);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.updateFootballMatchScore(match, -2, 1));

    }
    @Test
    @DisplayName("Finish match")
    @Order(10)
    void testFinishFootballMatch() {
        FootballMatch match = liveScoreboard.startFootballMatch("Uruguay", "Italy");

        Assertions.assertTrue(liveScoreboard.areTeamPairsValid(match.getHomeTeamName(), match.getAwayTeamName(), false));
        liveScoreboard.finishFootballMatch(match);

        Assertions.assertFalse(liveScoreboard.getSummary().contains(match));
    }

    @Test
    @DisplayName("Finish match - not existing match")
    @Order(11)
    void testFinishNotExistingFootballMatch() {
        FootballMatch match = new FootballMatch("Italy", "Spain",0,0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.finishFootballMatch(match));
    }

    @Test
    @DisplayName("Finish match - already finished match")
    @Order(12)
    void testAlreadyFinishedMatch() {
        FootballMatch match = liveScoreboard.startFootballMatch("Spain", "Brazil");
        liveScoreboard.finishFootballMatch(match);

        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.finishFootballMatch(match));
        Assertions.assertThrows(IllegalArgumentException.class, () -> liveScoreboard.finishFootballMatch(null));
    }

    @Test
    @DisplayName("Match summary")
    @Order(13)
    void testGetFootballMatchesSummary() {
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
    @DisplayName("Match summary - empty list")
    @Order(14)
    void testGetFootballMatchSummaryWithEmptyScoreboard() {
        List<FootballMatch> correctScoreboardOrder = new ArrayList<>();

        List<FootballMatch> currentScoreboardOrder = liveScoreboard.getSummary();
        Assertions.assertEquals(correctScoreboardOrder, currentScoreboardOrder, "Displayed order of football matches is not correct");
    }
}
