Feature:

  Background:
    * def port = karate.properties['server.port'] || karate.get('serverPort', 8080)
    * url 'http://localhost:' + port + '/api/todos'

  Scenario: simple crud flow
    # create first todo record and save the id
    * request { title: 'First', complete: false }
    * method post
    * status 200
    * match response == { id: '#string', title: 'First', complete: false }
    * def id = response.id

    # get newly created todo by id
    * path id
    * method get
    * status 200
    * match response == { id: '#(id)', title: 'First', complete: false }

    # get all todos and verify that the newly created todo is in the list
    * method get
    * status 200
    * match response contains [{ id: '#(id)', title: 'First', complete: false }]

    # create a second todo record and save the id
    * request { title: 'Second', complete: false }
    * method post
    * status 200
    * match response == { id: '#string', title: 'Second', complete: false }
    * def id = response.id

    # get all todos and verify that both the newly created ids are present
    * method get
    * status 200
    * match response contains [{ id: '#string', title: 'First', complete: false },{ id: '#(id)', title: 'Second', complete: false }]
