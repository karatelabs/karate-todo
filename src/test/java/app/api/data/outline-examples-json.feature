Feature: using nulls and inline json

  Scenario Outline:
    Given url 'https://reqres.in/api'
    And path 'register'
    And request { email: '#(email)', password: '#(password)' }
    When method post
    Then status 400
    * print response
    * match response == errorResponse

    Examples:
      | email! | password! | errorResponse!                                             |
      | null   | null      | { error: 'Missing email or username' }                     |
      | 'aa'   | 'bb'      | { error: 'Note: Only defined users succeed registration' } |

