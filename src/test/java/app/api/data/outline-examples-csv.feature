Feature: converting empty cells to null from a csv

  @setup
  Scenario:
    * def raw = read('examples.csv')
    * def convert = x => x || null
    * def data = raw.map(x => ({ email: convert(x.email), password: convert(x.password), error: x.error }))

  Scenario Outline:
    Given url 'https://reqres.in/api'
    And path 'register'
    And request { email: '#(email)', password: '#(password)' }
    When method post
    Then status 400
    * print response
    * match response == { error: '#(error)' }

    Examples:
      | karate.setup().data |
