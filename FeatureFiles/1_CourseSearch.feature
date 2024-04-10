Feature: Search courses and get titles,duration and rating of first 2 courses
  under beginner and English filter option

  @sanity
  Scenario: Search web development courses and apply filter options ( beginner, English)
    Given User opens the application
    When User search for "web development" courses and clicks on search button
    And select the English Language filter option
    And select the Beginner level filter option
    And user selects the first course
    Then user should naviagate to separate window
    And Collect the title,rating and duration of course in hours
    And After gathering the data. That child window should be closed
    When Now User select the second course with above filter options.
    Then user should naviagate to separate window
    And Collect the title,rating and duration of course in hours
    And After gathering the data. That child window should be closed
    And add data to excel


  
