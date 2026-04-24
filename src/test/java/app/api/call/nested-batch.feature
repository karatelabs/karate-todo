@ignore
Feature: middle-layer helper — invokes called.feature once per title

  # Sits between nested.feature (the caller) and called.feature (the leaf).
  # Takes an array of `{ title }` maps, calls the leaf helper for each, and
  # returns the collected ids.

  Scenario:
    * def results = call read('called.feature') titles
    * def ids = karate.map(results, function(r){ return r.id })
