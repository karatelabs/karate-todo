@ignore
Feature: reusable helper that creates a todo and returns its id

  # @ignore keeps this feature out of normal test runs — it only executes when
  # invoked via `call read('called.feature')`. A common pattern for test-data
  # setup helpers: the caller passes arguments (here, `title`), this feature
  # performs the work, and any variables it defines become the return value.

  Scenario:
    * url apiUrl + '/api/todos'
    * request { title: '#(title)', complete: false }
    * method post
    * status 201
    * def id = response.id
