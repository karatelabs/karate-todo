Feature: debugger demo - step through JavaScript

  Set breakpoints on the lines marked "breakpoint", then right-click this file > Debug.
  Paused in JavaScript, the Variables view shows the function's arguments and locals
  (Karate 2.1.4+) next to the scenario variables, and the Debug Console can evaluate
  expressions such as item.price * 2. Needs no server and is not part of any runner.

  Background:
    * def taxRate = 0.25

  Scenario: JavaScript in a docstring, a .js file and an eval block
    * def cart = [{ name: 'apple', price: 2, qty: 3 }, { name: 'pear', price: 5, qty: 1 }]

    # a function defined inline: step through it line by line
    * def subtotal =
    """
    function(items) {
      var total = 0;

      for (const item of items) {
        let line = item.price * item.qty;  // breakpoint: see item, line and total
        total += line;
      }
      return total;
    }
    """
    * def sub = subtotal(cart)
    * match sub == 11

    # a function in its own .js file
    * def invoice = read('invoice.js')
    * def bill = invoice(cart, taxRate)
    * match bill == { subtotal: 11, tax: 2.75, total: 13.75, lines: 2 }

    # an eval block runs in place
    * eval
    """
    var pricey = cart.filter(item => item.price > 3);

    karate.log('pricey items:', pricey.length);  // breakpoint
    """
