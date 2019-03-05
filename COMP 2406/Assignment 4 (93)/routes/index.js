// Nemanja Zutkovic
// 101085982

var url = require('url');
var sqlite3 = require('sqlite3').verbose(); //Verbose provides more detailed stack trace
var db = new sqlite3.Database('data/recipes');

db.serialize(function(){
	  //Make sure a couple of users exist in the database.
	  //User: username Password: password
      var sqlString = "CREATE TABLE IF NOT EXISTS users (userid TEXT PRIMARY KEY, password TEXT)";
      db.run(sqlString);
      sqlString = "INSERT OR REPLACE INTO users VALUES ('username', 'password')";
      db.run(sqlString);  	  
  });

exports.authenticate = function (request, response, next){
    /*
	Middleware to do BASIC http 401 authentication
	*/
    var auth = request.headers.authorization;
	//Auth is a base64 representation of (username:password) 
	//So we will need to decode the base64 
	if(!auth){
 	 	//Note here the setHeader must be before the writeHead
		response.setHeader('WWW-Authenticate', 'Basic realm="need to login"'); 
        response.writeHead(401, {'Content-Type': 'text/html'});
 		response.end();  
	}
	else{
        //Decode authorization header
		//Split on a space, the original auth 
		//Looks like  "Basic Y2hhcmxlczoxMjM0NQ==" and we need the 2nd part 
        var tmp = auth.split(' ');   		
		
		//Create a buffer and tell it the data coming in is base64 
        var buf = new Buffer(tmp[1], 'base64'); 
 
        //Read it back out as a string 
        //Should look like 'ldnel:secret'		
		var plain_auth = buf.toString();    
		
        //Extract the userid and password as separate strings 
        var credentials = plain_auth.split(':');      // split on a ':' 
        var username = credentials[0]; 
        var password = credentials[1]; 
		
		var authorized = false;
		//Check database users table for user
		db.all("SELECT userid, password FROM users", function(err, rows){
		for(var i=0; i<rows.length; i++){
		      if(rows[i].userid == username & rows[i].password == password) authorized = true;		     
		}
		if(authorized == false){
 	 	   //We had an authorization header by the user:password is not valid
		   response.setHeader('WWW-Authenticate', 'Basic realm="need to login"'); 
           response.writeHead(401, {'Content-Type': 'text/html'}); 
 		   response.end();  
		}
        else
		  next();				
		});
	}
}

function parseURL(request, response){
	var parseQuery = true; //ParseQueryStringIfTrue 
    var slashHost = true; //SlashDenoteHostIfTrue 
    var urlObj = url.parse(request.url, parseQuery , slashHost );
	return urlObj;
}

exports.recipes = function (request, response){
		var urlObj = parseURL(request, response);

		var sql = "SELECT id, recipe_name FROM recipes";

		if(urlObj.query['spices']) {
		    sql = "SELECT id, recipe_name FROM recipes WHERE spices LIKE '%" + urlObj.query['spices'] + "%'";		
		}

		if(urlObj.query['ingredients']) {
		    sql = "SELECT id, recipe_name FROM recipes WHERE ingredients LIKE '%" + urlObj.query['ingredients'] + "%'";			
		}	

		db.all(sql, function(err, rows){
	       response.render('recipes', {title: 'RECIPES', recipeEntries: rows});
 		});	
}

exports.recipeDetails = function(request, response){
	    var urlObj = parseURL(request, response);
        var recipeID = urlObj.path;

		recipeID = recipeID.substring(recipeID.lastIndexOf("/")+1, recipeID.length);
		var sql = "SELECT id, recipe_name, contributor, category, description, spices, source, rating, ingredients, directions FROM recipes WHERE id=" + recipeID;

		db.all(sql, function(err, rows){      
			response.render('recipeDetails', {title: 'RECIPE DETAILS', recipeEntries: rows});
		});
}