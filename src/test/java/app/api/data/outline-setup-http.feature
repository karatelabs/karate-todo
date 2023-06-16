Feature: using the results of an API call as a data-source

  @setup
  Scenario:
    * url 'https://jsonplaceholder.typicode.com/users'
    * method get

  Scenario Outline: id: ${id} | name: ${name}
    * url `https://httpbin.org/anything/${id}`
    * param name = name
    * method get

    Examples:
      | karate.setup().response |
