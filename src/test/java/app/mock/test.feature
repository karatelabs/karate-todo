Feature:

Scenario:
* def serverPort = karate.start({ mock: 'mock.feature', pathPrefix: '/api' }).port
* call read('../api/simple/simple.feature')