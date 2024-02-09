Feature: two api calls in a loop

  @setup
  Scenario:
    * def data = [{ id: 1, code: 200}, { id: 2, code: 200 }]

  Scenario Outline: id: ${id}
    * url `https://httpbin.org/anything/${id}`
    * method get
    * match responseStatus == code

    Examples:
      | karate.setup().data |