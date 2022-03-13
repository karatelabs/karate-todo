Feature:

Background:
* url 'http://localhost:8080/api/todos'

Scenario:
* request { name: 'Billie' }
* method post
* status 200    
* match response == { id: '#uuid', name: 'Billie' }
* def id = response.id

* path id
* method get
* status 200
* match response == { id: '#(id)', name: 'Billie' }

* method get
* status 200
* match response contains [{ id: '#(id)', name: 'Billie' }]

* request { name: 'Bob' }
* method post
* status 200    
* match response == { id: '#uuid', name: 'Bob' }
* def id = response.id

* path id
* method get
* status 200
* match response == { id: '#(id)', name: 'Bob' }

* method get
* status 200
* match response contains [{ id: '#uuid', name: 'Billie' },{ id: '#(id)', name: 'Bob' }]
