Feature: Submit match result
As player named Kol
I want to submit the result of matches
In order to update my classification

Background: 
    Given a calendar season where all players play among them
    And three players called Kol, Tis and Aka
 

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

	
Scenario: Final classification
    Given "Kol" results are "2-1" and "1-2" against "Tis" and "Aka"
    And "Tis" result is "0-3" against "Aka"
    When any of the players see their classification
    Then "Aka" is "first" with "5" wins and "1" lose
    And "Kol" is "second" with "3" wins and "3" loses
    And "Tis" is "third" with "1" wins and "5" loses
