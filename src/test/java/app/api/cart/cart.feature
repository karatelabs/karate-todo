Feature: Cart invoice

  Background:
    * def taxRate = 0.25

  Scenario: The invoice adds up the cart
    * def cart = [{ name: 'apple', price: 2, qty: 3 }, { name: 'pear', price: 5, qty: 1 }]

    * def subtotal =
    """
    function(items) {
      var total = 0;
      for (const item of items) {
        let line = item.price * item.qty;
        total += line;
      }
      return total;
    }
    """
    * def sub = subtotal(cart)
    * match sub == 11

    * def invoice = read('invoice.js')
    * def bill = invoice(cart, taxRate)
    * match bill == { subtotal: 11, tax: 2.75, total: 13.75, lines: 2 }

  Scenario Outline: A line of <name> costs price times quantity
    * def line = <price> * <qty>
    * match line == <total>

    Examples:
      | name  | price | qty | total |
      | apple | 2     | 3   | 6     |
