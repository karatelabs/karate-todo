Feature:

  Background:
    * configure driver = { type: 'chrome' }
    * driver 'http://localhost:8080'

  Scenario:
    * def find = read('find.js')
    * input('input[name=title]', 'One' + Key.ENTER)
    * def id = waitUntil(() => find('One'))
    * print 'created id:', id

    * url 'http://localhost:8080/api/todos'
    * path id
    * method get
    * status 200
    * match response == { id: '#(id)', title: 'One', complete: false }
