Feature: create submissions from csv

  Background:
    * url 'http://localhost:8080/api/submissions'

  Scenario Outline: ${account} - ${baseState}
    * def submission = 
    """
    {
      account: { id: '#(account)' },
      baseState: { code: '#(baseState)' },
      jobEffectiveDate: '#(jobEffectiveDate)',
      producerCode: { id: '#(producerCode)' },
      product: { id: '#(product)' }
    }
    """
    * request submission
    * method post
    * status 200
    * match response contains submission
    * match response.id == '#string'

    Examples:
        | read('submissions.csv') |
