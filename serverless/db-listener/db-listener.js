function main(params) {
    if(params._id) {
      console.log("New record created: ID:" + params._id + " - Name:" + params.name + " - Email:" + params.email);
    } else {
      console.log("No document received");
    }
  }
  
exports.main = main;