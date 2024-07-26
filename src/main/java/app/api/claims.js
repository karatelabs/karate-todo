session.claims = session.claims || [];
if (request.post) {
    let claim = request.body;
    claim.data.attributes.id = context.uuid();
    session.claims.push(claim);
    response.body = claim;
} else if (request.pathMatches('/{resource}/{id}')) {
    let id = request.pathParams.id;
    let index = session.claims.findIndex(c => c.data.attributes.id === id);
    if (index === -1) {
        response.status = 404;
    } else if (request.get) {
        response.body = session.claims[index];
    } else if (request.put) {
        let claim = request.body;
        claim.data.attributes.id = id;
        session.claims[index] = claim;
        response.body = claim;
    } else if (request.delete) {
        session.claims.splice(index, 1);
    }
} else {
    response.body = session.claims;
}

