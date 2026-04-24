# TODO: `waitUntil(() => find('One'))` times out on karate-js 2.0.5 — the zero-param
# arrow + closure-read(.js) pattern needs a separate investigation.
@todo @ui @hybrid @lock=ui
Feature: drive the UI then verify via the HTTP API

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
