@api @call
Feature: calling another feature file

  Scenario:
    * call read('classpath:app/api/call/called.feature')