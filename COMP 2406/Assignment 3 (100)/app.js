// Nemanja Zutkovic (101085982)

var express = require('express');
var requestModule = require('request');
var path = require('path');
var app = express();

app.use(express.static(path.join(__dirname, 'public')));
const API = 'b4215796be2bc959d34290a9c1e3dc94';

app.get('/recipes', (request, response) => {
  let food = request.query.ingredients;
  if(!food) {
    return response.sendFile(__dirname + "/views/recipes.html");
  }
  const url = `http://food2fork.com/api/search?q=${food}&key=${API}`;
  requestModule.get(url, (err, res, data) => {
    return response.contentType('application/JSON').json(data);
  });
});

app.get('/instantrecipe', (request, response) => {
   return response.sendFile(__dirname + "/views/recipes.html");
});

app.listen(3000, (err) => {
	if (err)
		console.log(err);
	else
		console.log(`Port listening on 3000`);
});