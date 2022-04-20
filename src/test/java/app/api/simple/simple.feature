Feature:

Background:
* def port = karate.properties['server.port'] || karate.get('serverPort', 8080)
* url 'http://localhost:' + port + '/api/todos'

Scenario:
* request { title: 'First', complete: false }
* method post
* status 200    
* match response == { id: '#string', title: 'First', complete: false }
* def id = response.id

* path id
* method get
* status 200
* match response == { id: '#(id)', title: 'First', complete: false }

* method get
* status 200
* match response contains [{ id: '#(id)', title: 'First', complete: false }]

* request { title: 'Second', complete: false }
* method post
* status 200
* match response == { id: '#string', title: 'Second', complete: false }
* def id = response.id

* method get
* status 200
* match response contains [{ id: '#string', title: 'First', complete: false },{ id: '#(id)', title: 'Second', complete: false }]
