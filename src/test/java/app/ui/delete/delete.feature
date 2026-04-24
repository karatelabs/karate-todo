# Not safe to run in parallel — clears shared app state. Opt in explicitly.
@ignore @ui @cleanup
Feature: delete every todo one by one

  Background:
    * driver serverUrl

  Scenario:
    * def remove =
    """
    function() {
      if (!exists('.border-bottom div')) {
        return true;
      }
      click('.text-end button');
    }
    """
    * waitUntil(remove)
