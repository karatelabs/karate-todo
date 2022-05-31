session.todos = session.todos || [];
if (request.post) {
    let todo = request.body;
    todo.id = context.uuid();
    session.todos.push(todo);
    response.body = todo;
} else if (request.pathMatches('/{resource}/{id}')) {
    let id = request.pathParams.id;
    let index = session.todos.findIndex(c => c.id === id);
    if (index === -1) {
        response.status = 404;
    } else if (request.get) {
        response.body = session.todos[index];
    } else if (request.put) {
        let todo = request.body;
        todo.id = id;
        session.todos[index] = todo;
        response.body = todo;
    } else if (request.delete) {
        session.todos.splice(index, 1);
    }
} else {
    // context.delay(1000);
    response.body = session.todos;
}
