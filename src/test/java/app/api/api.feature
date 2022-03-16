Feature:

Background:
* url 'http://localhost:8080/api/todos'

Scenario:
* request { title: 'First', completed: false }
* method post
* status 200    
* match response == { id: '#number', title: 'First', completed: false }
* def id = response.id

* path id
* method get
* status 200
* match response == { id: '#(id)', title: 'First', completed: false }

* method get
* status 200
* match response contains [{ id: '#(id)', title: 'First', completed: false }]

* request { title: 'Second', completed: false }
* method post
* status 200    
* match response == { id: '#number', title: 'Second', completed: false }
* def id = response.id

* path id
* method get
* status 200
* match response == { id: '#(id)', title: 'Second', completed: false }

* method get
* status 200
* match response contains [{ id: '#number', title: 'First', completed: false },{ id: '#(id)', title: 'Second', completed: false }]
