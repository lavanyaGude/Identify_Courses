Feature: Language Learning Feature
  clicking on the each language

  @sanity @regression
  Scenario: selecting  All the languages under language learning category
    and gathering the number of courses available for each level

    Given User opens the application
    When user clicks on Explore dropdown
    And select the language learning subject
    And select All Language Courses section
    Then it should load a new page with all courses
    And take all the available languages for each level and extracting number of courses available for each level
