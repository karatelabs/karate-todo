# TODO(karate-js): re-enable once karate-js 2.0.5 parses `() => ...`
# zero-param arrow functions correctly.
@todo
Feature:

  Background:
    * driver serverUrl

  Scenario:
    * def find = read('find.js')
    * input('input[name=title]', 'One' + Key.ENTER)
    * def id = waitUntil(() => find('One'))
    * print 'created id:', id

    * url apiUrl + '/api/todos'
    * path id
    * method get
    * status 200
    * match response == { id: '#(id)', title: 'One', complete: false }
