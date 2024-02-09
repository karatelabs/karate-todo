Feature: helper to clear all submissions
  
Background:
    * url 'http://localhost:8080'

Scenario:
  * path 'api', 'reset'
  * method get
  * status 200