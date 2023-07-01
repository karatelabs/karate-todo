Feature:

Scenario:
* def urlBase = 'http://localhost:' + karate.start({ mock: 'mock.feature', pathPrefix: '/api' }).port
* call read('../api/simple/simple.feature')