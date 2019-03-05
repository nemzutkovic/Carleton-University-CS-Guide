// Nemanja Zutkovic
// 101085982

var http = require('http');
var express = require('express');
var path = require('path');
var logger = require('morgan');

var app = express(); //Create express middleware dispatcher

const PORT = process.env.PORT || 3000;

// View Engine Setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs'); //Use hbs handlebars wrapper

app.locals.pretty = true; //To generate pretty view-source code in browser

//Read routes modules
var routes = require('./routes/index');

//Some logger middleware functions
function methodLogger(request, response, next){           
		   console.log("METHOD LOGGER");
		   console.log("================================");
		   console.log("METHOD: " + request.method);
		   console.log("URL:" + request.url);
		   next(); //Call next middleware registered
}
function headerLogger(request, response, next){           
		   console.log("HEADER LOGGER:")
		   console.log("Headers:")
           for(k in request.headers) console.log(k);
		   next(); //Call next middleware registered
}

//Register middleware with dispatcher
//ORDER MATTERS HERE
//Middleware
app.use(routes.authenticate); //Authenticate user
app.use(logger('dev'));

//Routes	
app.get('/recipes', routes.recipes);
app.get('/recipe/*', routes.recipeDetails);

//Start Server
app.listen(PORT, err => {
  if(err) console.log(err)
  else {console.log(`Server listening on port ${PORT}`)}
});