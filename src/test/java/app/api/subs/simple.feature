Feature: create and get a submission

  Background:
    * url 'http://localhost:8080/api/submissions'

  Scenario: simple crud flow
    * def submission = 
    """
    {
      account: { id: 'pc:401' },
      baseState: { code: 'CA' },
      jobEffectiveDate: '2022-08-01',
      producerCode: { id: 'pc:16' },
      product: { id: 'PersonalAuto' }
    }
    """

    # create submission and save the id
    * request submission
    * method post
    * status 200
    * match response contains submission
    * match response.id == '#string'
    * def id = response.id

    # get newly created submission by id
    * path id
    * method get
    * status 200
    * match response contains { id: '#(id)' }
    * match response contains 'test'

