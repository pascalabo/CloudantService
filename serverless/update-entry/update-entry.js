function main(params) {
    if(!params.doc){
        return Promise.reject({ error: 'no doc is provided'});
    }
	return { doc:{...params.doc, createdAt:new Date()}};
}

exports.main = main;