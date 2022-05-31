Feature:

  Scenario:
    * def helper = Java.type('app.api.interop.Helper')
    * def result = helper.doWork('hello world')
    * match result == { "message": "hello world" }
