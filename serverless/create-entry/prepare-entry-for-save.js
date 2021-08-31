function main(params) {
    if (!params.name || !params.email) {
      return Promise.reject({ error: 'no name or email provided'});
    }
  
    return {
      doc: {
         createdAt: new Date(),
         name: params.name,
         email: params.email
      }
    };
  }

exports.main = main;
