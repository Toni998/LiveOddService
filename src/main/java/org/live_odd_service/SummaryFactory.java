package org.live_odd_service;

public class SummaryFactory {
    public static WorldCup createWorldCup() {
        return new WorldCup();
    }

    public static LiveScoreboard createLiveScoreboard() {
        return new LiveScoreboard(createWorldCup());
    }
}
