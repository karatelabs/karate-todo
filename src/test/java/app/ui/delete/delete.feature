Feature:

  Background:
    * configure driver = { type: 'chrome' }
    * driver 'http://localhost:8080'

  Scenario:
    * def delete = 
    """
    function() {
      if (!exists('.border-bottom div')) {
        return true;
      }
      click('.text-end button');
    }
    """
    * waitUntil(delete)
