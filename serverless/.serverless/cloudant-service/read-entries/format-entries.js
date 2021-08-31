function main(params) {
    return {
      entries: params.rows.map((row) => { return {
        name: row.doc.name,
        email: row.doc.email,
        _id:row.doc._id,
        _rev:row.doc._rev,
        createdAt:row.doc.createdAt
      }})
    };
  }

exports.main = main;