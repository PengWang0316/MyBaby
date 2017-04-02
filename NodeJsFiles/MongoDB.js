var mongodb = require('mongodb');

var MongodbClient = mongodb.MongoClient;

var dbUrl = 'mongodb://mybaby:xyz19822@34.208.114.192:27017/MyBaby';

/*
* The function that is used to get user id based on its Facebook id.
*/
exports.getUserIdFromFacebookOrGoogleId = function (facebookId, googleId, callback){
	// facebookId and googleId may be undefined
	if (facebookId == undefined) facebookId = "";
	if (googleId == undefined) googleId = "";
	connectToDb(function(db){
		var collection = db.collection("users");		
		collection.find({"$or":[{"facebookId":facebookId}, {"googleId":googleId}]}).toArray(function(err,result){
			if (err) console.log("Something goes worry");
			else if (result!=null) callback(result[0]!=undefined ? result[0]._id.toString() : "");
		});
	});	
}

 /*
* The function that is used to insert a new user to database and get id back.
*/
exports.insertUser = function (name, facebookId, googleId, email, callback){
	connectToDb(function(db){
		console.log(">>>>>>>>>> MongonDB.insertUser <<<<<<<<<<");
		console.log("name: "+name);
		console.log("facebookId: "+facebookId);
		console.log("googleId: "+googleId);
		console.log("email: "+email);
		var collection = db.collection("users");
		collection.insertOne({name:name, facebookId:facebookId, googleId:googleId, email:email},function(err,result){
			console.log("The results id: "+ result.insertedId.toString());
			callback(result.insertedId.toString());
		});
				
		console.log(">>>>>>>>>>>> Finished insert <<<<<<<<<<<<<");

		
	});
}

/*
* Use to execute the database
* Other function can call it to get the connection.
* Pass a function that contains the executed code.
*/
function connectToDb(executeFunction){
	MongodbClient. connect(dbUrl,function(err,db){
	if (err){
		console.log("Unable to connect to the mongoDB server. Error:", err);
	}else{
		console.log("Connection of MongonDB was established.");
		// Run given mehtod
		executeFunction(db);
}
db.close();
});
}
