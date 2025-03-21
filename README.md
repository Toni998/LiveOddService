Live Scoreboard

-Live scorebaord is a simple library which simulates socreboard for representing ongoing matches.<br />
-Live scoreboard can start, update, finish and output football matches.<br />

Functionalities : <br />
-Starting football match<br />
-Checking if teams are participating world cup <br />
-Checking if given team pair is a valid one <br />
-Updating apsulute score<br />
-Getting summary for all matches and apply sorting.<br />

The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
This should capture following parameters:
a. Home team
b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
same total sc

Architecure : 
FootballMatch - class representing a football match containg informations about football match and funtions for retriving data for selected match
LiveScoreboard - Simulation of scoreboard for live football matches which is responsible for processing data about football match for starting, updating, finish football match.
LiveOddServiceTest - class where all tests are implemented. Applicatipn is implemented in TDD design.
Main - main program where the football matches are defined and applied sorting for getting summary as in the example above.
SummaryFactory - class used for DI container

Testing is done with Junit5 framework

Test ovreview :
- Participate world cup - responsible for checking is the provided team/team pair allowed to participate world cup.
- Start football match - responsible for starting football match. Cheks if provided names are correct and is inital score is 0-0. No validation for partcipation is done
- Start football match - already started match - responsible for starting already started match. Throws exception is match is already started.
- Start football match - valid names - responsible for starting match with valid names and team pairs. Asserting true is expected.
- Start football match - invalid names - responsible for starting match with invalid names, throws exception  or false if pairs or provided team name is not correct.
- Update match score - responsible for starting football match and update the score.
- Update match score - not existing match - responsible for staring football match but the match is not added to scoreboard. Exception is asserted if update for football match that's not on scorebaord is requested.
- Update match score - negative score - responsible for starting footbal match, update the score with negative result. Exception is asserted if negative scores provided to update function.
- Update match score - finished match - responsible for starting football match, finish it. Try to update score on finished match. Exception is asserted if finished match is updated.
- Finish match - responsible for starting football match. Football match is finished and removed from scoreboard. Exception is asserted when checked if the match is still displayed on scoreboard.
- Finish match - not existing match - responsible for starting football match but not added on scoreboard. Exception is asserted if that match is finished.
- Finish match - already finished match - responsible for starting footbal match and finish it. Exceptions is asserted if that match is finished again.
- Match summary - responsible for starting few matches. Updating scores for all matches. List of expected football matches on scoreboard is created. Assertion is done for getting equal list of expected order.
- Match summary - empty list - responsible for testing output when empty scoreboard provided for getting summary. 

How to run ?<br />
-Clone LiveOddService repository.<br />
-Open in desired IDE (implemented in Intelij)<br />
-Add dependencies<br />
-For running tests -> open LiveOddServiceTest and run it<br />
-For running main program and checking summary from requirements -> run main program<br />

Enjoy !


