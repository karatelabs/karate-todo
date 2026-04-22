function fn() {
  // serverUrl: where the browser navigates (inside the Chrome container this is
  // host.docker.internal, on the host it's just localhost).
  var serverUrl = karate.properties['serverUrl'] || 'http://localhost:8080';
  // apiUrl: where karate's HTTP client (running on the host, not in a container)
  // hits the app. Differs from serverUrl when the browser is containerized.
  var apiUrl = karate.properties['apiUrl'] || serverUrl;
  karate.configure('driver', { type: 'chrome' });
  return { serverUrl: serverUrl, apiUrl: apiUrl };
}
