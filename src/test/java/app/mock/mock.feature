Feature:

Background:
* def uuid = function(){ return java.util.UUID.randomUUID() + '' }
* def todos = {}

Scenario: pathMatches('/api/todos') && methodIs('post')
    * def todo = request
    * def id = uuid()
    * todo.id = id
    * todos[id] = todo
    * def response = todo

Scenario: pathMatches('/api/todos')
    * def response = $todos.*

Scenario: pathMatches('/api/todos/{id}')
    * def response = todos[pathParams.id]

Scenario:
    * def responseStatus = 404
