function main(params) {
    if (!params.docid || !params.docrev) {
  return Promise.reject({ error: 'docid and docrev is required'});
}
  return { docid: params.docid,docrev:params.docrev };
}

exports.main = main;