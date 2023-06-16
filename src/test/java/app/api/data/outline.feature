Feature: simple data driven testing

  Background:
    * url 'http://localhost:8080/api/todos'

  Scenario Outline:
    * request { title: '#(title)', complete: false }
    * method post
    * match response == { id: '#string', title: '#(title)', complete: false }
    * status 200

    Examples:
      | title |
      | One   |
      | Two   |
      | Three |
