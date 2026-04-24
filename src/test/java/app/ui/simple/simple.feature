@smoke @ui @crud @lock=ui
Feature: add a todo via the UI

  Background:
    * driver serverUrl

  Scenario: add and verify a todo
    * screenshot()
    * input('input[name=title]', 'Task One' + Key.ENTER)
    * waitFor('.border-bottom')
    * screenshot()
