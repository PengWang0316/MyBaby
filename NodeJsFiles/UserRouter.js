var express = require("express");
var mongoDB = require("./MongoDB");


// var jsonParser = bodyParser.json();
var router = express.Router();


var userApiPrefixUrl = "/api/v1/users";

/*
*	Get a user's id from the database based on giving Facebook id or Google id
*/
router.get(userApiPrefixUrl,function(req,res){
	// If facebookId is given, fetch the user id back.	 
	var facebookId = req.query.facebookId;
	var googleId = req.query.googleId;
	console.log("Try to find record with facebookId:"+facebookId + "  or googleId: "+ googleId);
	// If facebookId or googleId is not found in url param, it will be undefinded
	// The result should be the Id of the user.
		mongoDB.getUserIdFromFacebookOrGoogleId(facebookId, googleId, function(result){
			res.send(result);
		});

	/*if(facebookId!=undefined && facebookId!=""){
		// The result should be the Id of the user.
		mongoDB.getUserIdFromFacebookOrGoogleId(facebookId, function(result){
			res.send(result);
		});
	}else if (googleId!=undefined && googleId!=""){
		// The result should be the Id of the user.
		mongoDB.getUserIdFromFacebookOrGoogleId(googleId, function(result){
			res.send(result);
		});
	}else {
		res.send("");
	}*/
});

router.post(userApiPrefixUrl, function(req,res){
	console.log("req.body: "+req.body);
	var name = req.body.name;
	if (name==undefined || name==""){
		console.log("Name field should not be empty.");
		res.end();
	}
	var facebookId = req.body.facebookId;
	var googleId = req.body.googleId;
	var email = req.body.email;
	mongoDB.insertUser(name,facebookId,googleId,email,function(result){
		// The expected result is new id 
		res.send(result);
	});
	
});

module.exports = router;