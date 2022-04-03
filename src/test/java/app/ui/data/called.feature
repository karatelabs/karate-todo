@ignore
Feature:

Scenario Outline:
* input('input[name=title]', title + Key.ENTER)
* def found =
"""
function() {
  let rows = scriptAll('.border-bottom', '_.textContent');
  let count = rows.length;
  if (count && rows[count-1] == title) {
    return rows[count-1]
  } else {
    return null;
  }
}
"""
* waitUntil(found)

Examples:
| title |
| One   |
| Two   |
| Three |
