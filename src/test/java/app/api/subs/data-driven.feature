Feature: create submissions from table

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
    * match response.product.id == 'PersonalAuto'

    Examples:
      | account | baseState | jobEffectiveDate | producerCode | product        |
      | pc:401  | CA        | 2022-08-01       | pc:16        | PersonalAuto   |  
      | pc:402  | TX        | 2023-01-02       | pc:17        | CommercialAuto |  
      | pc:403  | TX        | 2023-02-02       | pc:18        | PersonalAuto   |  