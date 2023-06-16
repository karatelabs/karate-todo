Feature: generating data using a function

  @setup
  Scenario:
    * def generator = 
    """
    function(i){ 
      if (i == 10) return null; 
      return { name: `cat${i}`, age: i };
    }
    """

  Scenario Outline:
    * print __row

    Examples:
      | karate.setup().generator |
