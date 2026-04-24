@api @call
Feature: using a called feature to set up test data

  # Shows the simplest call pattern: delegate the "create a todo" setup to a
  # reusable helper feature, capture the return value (a map of every variable
  # the helper defined), and use it to drive the real assertions.

  Scenario: create a todo via a helper, then verify it exists
    * def created = call read('called.feature') { title: 'from-caller' }

    * url apiUrl + '/api/todos/' + created.id
    * method get
    * status 200
    * match response == { id: '#(created.id)', title: 'from-caller', complete: false }
