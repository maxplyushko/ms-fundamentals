Feature: Resource service test
  Scenario: New song resource was uploaded
    Given I set POST resource API endpoint
    When I send a POST HTTP request with multipart file with "Original song name" name
    Then id 1 of the song resource should be returned