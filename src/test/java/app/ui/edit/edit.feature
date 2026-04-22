# TODO(karate-js): re-enable once karate-js 2.0.5 fixes the JS regressions
# surfaced by `waitFor(...).mouse().doubleClick()`.
@todo
Feature:

  Background:
    * driver serverUrl

  Scenario:
    * input('input[name=title]', 'Task One' + Key.ENTER)
    * waitFor('.border-bottom div').mouse().doubleClick()
    * waitFor('.border-bottom input').clear().input('Task Edit')
    * mouse('h1').click()
    * waitForText('.border-bottom', 'Task Edit')
