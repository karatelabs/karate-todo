session.submissions = session.submissions || [];
if (request.post) {
    let submission = request.body;
    submission.id = context.uuid();
    session.submissions.push(submission);
    response.body = submission;
} else if (request.pathMatches('/{resource}/{id}')) {
    let id = request.pathParams.id;
    let index = session.submissions.findIndex(c => c.id === id);
    if (index === -1) {
        response.status = 404;
    } else if (request.get) {
        response.body = session.submissions[index];
    } else if (request.put) {
        let submission = request.body;
        submission.id = id;
        session.submissions[index] = submission;
        response.body = submission;
    } else if (request.delete) {
        session.submissions.splice(index, 1);
    }
} else {
    response.body = session.submissions;
}

