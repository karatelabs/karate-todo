Feature:

  Background:
    * configure driver = { type: 'chrome' }
    * driver 'http://localhost:8080'

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
