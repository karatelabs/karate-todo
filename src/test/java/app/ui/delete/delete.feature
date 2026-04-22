# TODO(karate-js): re-enable once karate-js 2.0.5 fixes arrow/closure regressions.
# Also not ideal for CI in parallel — clears shared app state.
@todo
Feature:

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
