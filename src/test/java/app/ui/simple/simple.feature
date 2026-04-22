Feature:

  Background:
    * driver serverUrl

  Scenario: add a todo via the UI
    * input('input[name=title]', 'Task One' + Key.ENTER)
    * waitFor('.border-bottom')
