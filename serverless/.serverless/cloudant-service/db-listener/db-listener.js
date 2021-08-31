function main(data) {
    if(data._id) {
      console.log("New record created for user: " + data._id + " - " + data.name);
    } else {
      console.log("No document received");
    }
  }
  
exports.main = main;