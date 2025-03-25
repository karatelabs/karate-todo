Feature:

  Scenario: calling static method
    * def helper = Java.type('app.api.interop.Helper')
    * def result = helper.doWork('hello world')
    * match result == { "message": "hello world" }

  Scenario:
    * def helper = Java.type('app.api.interop.Helper')
    * def encoded = karate.readAsBytes('encoded.bin')
    * def decoded = helper.fromBrotli(encoded)
    * match decoded == '{ "hello": "world" }'
    * def json = karate.fromString(decoded)
    * match json == { hello: 'world' }