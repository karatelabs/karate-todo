@ignore
Feature:

  Scenario Outline:
    * input('input[name=title]', title + Key.ENTER)
    * def find =
    """
    function fn() {
      let rows = scriptAll('.border-bottom div', '_.textContent');
      let count = rows.length;
      if (count && rows[count-1] == title) {
        return rows[count-1]
      } else {
        return null;
      }
    }
    """
    * waitUntil(find)

    Examples:
      | title |
      | One   |
      | Two   |
      | Three |
