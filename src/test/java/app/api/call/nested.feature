@api @call
Feature: nested called features — a chain of reusable helpers

  # Demonstrates multi-level call nesting. The top scenario calls a "batch"
  # helper, which in turn calls the single-item helper (called.feature).
  #
  # In the IntelliJ / VS Code test tree and in karate-events.jsonl, only the
  # top-level scenario below shows as a first-class test — the two nested
  # helpers execute as part of it, not as sibling tests.

  Scenario: create several todos by chaining helpers
    * def titles = [{ title: 'nested-a' }, { title: 'nested-b' }, { title: 'nested-c' }]
    * def created = call read('nested-batch.feature') { titles: '#(titles)' }
    * match created.ids == '#[3]'

    # sanity-check one of the created todos end-to-end
    * url apiUrl + '/api/todos/' + created.ids[0]
    * method get
    * status 200
    * match response.title == 'nested-a'
