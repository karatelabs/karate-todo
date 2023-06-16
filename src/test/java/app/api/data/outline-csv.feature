Feature:

  Scenario Outline: dynamic ${__num + 1}
    # dynamic outline comment
    * print 'row:', __row
    
    Examples:
      | read('data.csv') |