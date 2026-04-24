# TODO: the called `waitUntil(find)` loop times out on karate-js 2.0.5 —
# needs a separate investigation into the scenario-outline + closure interaction.
@todo @ui @data-driven @lock=ui
Feature: data-driven UI flow via a called feature

  Scenario:
    * driver serverUrl
    * call read('called.feature')

