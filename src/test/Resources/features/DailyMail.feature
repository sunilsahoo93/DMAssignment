@Regression @UI
#Author: Sunil Kumar Sahoo
#Creation Date: 29 Oct 2022

Feature: SDET Home GUI Test
  Scenario Outline: Automate test scenario for Daily Mail
    Given user navigates to daily mail home page
    When user verifies webpage for date and time
    Then it should match current date and time
    When user navigates to the Sport menu
    Then primary navigation color and secondary navigation color should match
    When user click on the Football sub navigation item
    And clicks the first article displayed
    And traverses to the gallery  image and clicks the gallery icon on the right hand side corner with numbers
    Then gallery loads on full page
    And user verifies that it has previous and next buttons
    When user click on the next and previous buttons, it navigates to appropriate gallery picture
    When user clicks on the Facebook share icon
    Then user verifies that Facebook modal dialog opens
    When user navigates and clicks on the full screen button of a video embedded within the article
    Then user should be able to view the video in full screen and minimized mode
    When user navigates to bottom right of the article page to the Premier League table section
    And exhibit the points or positions from the Premier League table for the team <Team>

    Examples:
      | Team      |
      | Liverpool |

