Feature: mixing 2 http calls but the second one in a loop

  Scenario:
    * url 'https://jsonplaceholder.typicode.com/users'
    * method post
    * table data
      | id | name  |
      | 1  | 'foo' |
      | 2  | 'bar' |
    * call read('@called') data

  @ignore @called
  Scenario:
    * url `https://httpbin.org/anything/${id}`
    * param name = name
    * method get
