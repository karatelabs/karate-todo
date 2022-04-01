Feature:

Background:
* configure driver = { type: 'chrome' }

Scenario:
* driver 'http://localhost:8080'
* waitFor('h1')
* input('input[name=title]', 'Item One' + Key.ENTER)
* delay(1000)
* screenshot()

