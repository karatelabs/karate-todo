# TODO: `waitFor(...).mouse().doubleClick()` fails with `.mouse is not a function`
# on karate-js 2.0.5 — needs a separate investigation into the driver element API.
@todo @ui @edit @lock=ui
Feature: edit an existing todo via double-click

  Background:
    * driver serverUrl

  Scenario:
    * input('input[name=title]', 'Task One' + Key.ENTER)
    * waitFor('.border-bottom div').mouse().doubleClick()
    * waitFor('.border-bottom input').clear().input('Task Edit')
    * mouse('h1').click()
    * waitForText('.border-bottom', 'Task Edit')
