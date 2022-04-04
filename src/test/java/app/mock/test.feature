Feature:

Scenario:
* def port = karate.start('mock.feature').port
* url 'http://localhost:' + port + '/todos'
* request { title: 'First', complete: false }
* method post
* status 200
* match response == { id: '#string', title: 'First', complete: false }
