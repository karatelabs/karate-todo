function fn() {
  let urlBase = java.lang.System.getenv('URL_BASE');
  karate.log('*** perf mode, urlBase resolved to:', urlBase);
  return { urlBase: urlBase };
}
