Feature: reading payloads from json files

  Scenario:
    * def data = read('data.json')
    * url 'https://httpbin.org/anything'
    * request data
    * method post
    * status 200
    * match response.json == data