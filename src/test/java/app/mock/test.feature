Feature:

Scenario:
* def serverPort = karate.start({ mock: 'mock.feature', pathPrefix: '/api' }).port
* call read('classpath:app/api/simple/simple.feature')