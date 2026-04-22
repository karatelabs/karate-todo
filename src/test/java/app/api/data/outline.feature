@api @data-driven @crud
Feature: simple data driven testing

  Background:
    * url apiUrl + '/api/todos'

  Scenario Outline: using title: ${title}
    * request { title: '#(title)', complete: false }
    * method post
    * status 201
    * match response == { id: '#string', title: '#(title)', complete: false }

    Examples:
      | title |
      | One   |
      | Two   |
      | Three |
