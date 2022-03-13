if (!session.items) {
  session.items = [];
}
if (request.post) {
  let item = request.body;
  item.id = context.uuid();
  session.items.push(item);
  response.body = item;
} else if (request.pathMatches('/{resource}/{id}')) {
  let index = session.items.findIndex(c => c.id === request.pathParams.id);
  if (index === -1) {
    response.status = 404;
  } else if (request.get) {
    response.body = session.items[index];
  } else if (request.put) {
    let item = request.body;
    item.id = request.pathParams.id;
    session.items[index] = item;
    response.body = item;
  } else if (request.delete) {
    session.items.splice(index, 1);
  }
} else {
  response.body = session.items;
}
