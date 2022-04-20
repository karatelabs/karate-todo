let count = session.todos ? session.todos.length : 0;
session.todos = [];
response.body = {deleted: count};