Feature: Director Name
  Scenario: Validate director name in Wikipedia vs IMDB
    Given a list of movie name and urls
    When user tries to extract the director names on wikipedia and imdb
    Then the director names should match
