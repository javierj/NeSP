




Feature Sign up in league
As a new player named Newt
I want to sign myself into the league
In order to play matches and have a classification


Scenario: Update results
    Given A result for that match of 2-1 (best of three)
    When "Kol" introduces "2" wins for him and "1" for "Tis"
    Then "Kol" is in the classification with "2" wins 
    And "Tis" is behind him with "1" victory

	
Scenario: Climbing the classification
    Given "Kol" has 2 wins in the classification
    And "Kol" plays a match against "Aka"
    When "Kol" introduces "1" win for him and "2" wins for "Aka"
    Then "Kol"'s classification is updated with "3" wins 

	
Scenario: Bad battletag id
    Given Signing up a new user
    When "Newt" introduces "Abs#123" as battletag 
    Then System abort due the battletag lack one figure

	

	
