Feature:

Scenario: match examples
* def cat = 
  """
  {
    name: 'Billie',
    kittens: [
      { id: 23, name: 'Bob' },
      { id: 42, name: 'Wild' }
    ]
  }
  """

# using js-style access patterns
* match cat.kittens[0].name == 'Bob'

# using Json-Path
* match cat.kittens[*].id == [23, 42]

# contains
* match cat.kittens[*].id contains 23
* match cat.kittens[*].id contains [42, 23]

# any depth
* match cat..name == ['Billie', 'Bob', 'Wild']

# accessing nested objects
* match cat.kittens contains [{ id: 42, name: 'Wild' }, { id: 23, name: 'Bob' }]

# using fuzzy-matching
* match cat.kittens contains { id: 42, name: '#string' }
