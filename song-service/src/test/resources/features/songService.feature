Feature: Song service management
  Scenario: Add a new song
    Given A song with name "Test song"
    When We add that song
    Then Song with name "Test song" should be added