let count = session.todos ? session.todos.length : 0;
session.todos = [];
session.submissions = [];
response.body = {deleted: count};