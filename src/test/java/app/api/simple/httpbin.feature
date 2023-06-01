Feature: simple requests

  Scenario: simple POST and GET
    * url 'https://httpbin.org/anything'
    * request { myKey: 'myValue' }
    * method post
    * status 200
    * match response.json == { myKey: 'myValue' }

    * path response.json.myKey
    * method get
    * status 200
