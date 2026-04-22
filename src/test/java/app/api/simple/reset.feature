@api @setup
Feature: helper to clear all todos

Scenario:
  * url apiUrl
  * path 'api', 'reset'
  * method get
  * status 200