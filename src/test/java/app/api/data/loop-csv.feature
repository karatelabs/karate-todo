Feature: looping over a json array converted from a csv

  Scenario:
    * def raw = read('examples.csv')
    * def convert = x => x || null
    * def data = raw.map(x => ({ email: convert(x.email), password: convert(x.password), error: x.error }))
    * call read('@register') data

  @ignore @register
  Scenario:
    Given url 'https://reqres.in/api'
    And path 'register'
    And request { email: '#(email)', password: '#(password)' }
    When method post
    Then status 400
    * print response
    * match response == { error: '#(error)' }