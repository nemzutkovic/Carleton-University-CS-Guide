// Nemanja Zutkovic
// 101085982

var fs = require('fs'); //built in node file system module
var lineReader = require('line-reader'); //npm install line-reader
var sqlite3 = require('sqlite3').verbose(); //npm install sqlite3

function writeRecipesToFile(recipes){
  var filePath = 'recipes.db';
  var outStream = fs.createWriteStream(filePath);
  for (i in recipes) {
    outStream.write(`${i}: ${recipes[i].recipe_name}\n`);
  }
  outStream.end();
  outStream.on('finish', function() {
    console.log('Writing to ' + filePath + ' complete');
  });
}

function writeRecipesToDatabase(recipes){
  var db = new sqlite3.Database('recipes');
  db.serialize(function() {

     //drop existing table from database
     var sqlString = "DROP TABLE IF EXISTS recipes";   
     db.run(sqlString);
     
   //create table in the current database  
     sqlString = "CREATE TABLE IF NOT EXISTS recipes (id INTEGER PRIMARY KEY, recipe_name TEXT, contributor TEXT, category TEXT, description TEXT, spices TEXT, source TEXT, rating TEXT, ingredients TEXT, directions TEXT)";
   
     db.run(sqlString);

     //use prepared statements to help prevent sql injection
     /*
     Prepared statements consist of SQL with ? parameters for data.
     Prepared statements are pre-compiled as SQL so that one cannot
     insert, or inject, SQL commands for the ? parameters.
     */
     var stmt = db.prepare("INSERT INTO recipes (recipe_name,contributor,category,description,spices,source,rating,ingredients,directions) VALUES (?,?,?,?,?,?,?,?,?)");
     for (var i = 0; i < recipes.length; i++) {
        recipe = recipes[i];
        stmt.run(recipe.recipe_name, recipe.contributor, recipe.category, recipe.description, recipe.spices, recipe.source, recipe.rating, recipe.ingredients, recipe.directions);
     }
     stmt.finalize();
  
     db.each("SELECT id, recipe_name, contributor, category, description, spices, source, rating, ingredients, directions FROM recipes", function(err, row) {
         console.log(row.id + ": " +
                 row.recipe_name + " " +
                 row.contributor + " " +
                 row.category + " " +
                 row.description + " " +
                 row.spices + " " +
                 row.source + " " +
                 row.rating + " " +
                 row.ingredients + " " +
                 row.directions);
     });  

  });
  db.close();
}


//FILE PARSING CODE
function isTag(input){
  return input.startsWith("<");
}
function isOpeningTag(input){
  return input.startsWith("<") && !input.startsWith("</");
}
function isClosingTag(input){
  return input.startsWith("</");
}

var dataString = ''; //data between tags being collected
var openingTag = ''; //xml opening tag
var recipes = []; //recipes parsed
var recipe = {};  //recipe being parsed

//read aLaCarteData xml file one line at a time
//and parse the data into a JSON object string
//PRERQUISITE: the file must be validated XML

lineReader.eachLine(
    'aLaCarteData_rev3.xml', 
    function(line, last) { 
      str = line.trim();
    if(isOpeningTag(str)){
      openingTag = str;
      dataString = '' //clear data string
    }
    else if(isClosingTag(str)){ 
       if(str === '</recipe_name>') {
         recipe.recipe_name = dataString;
       }
       else if(str === '</contributor>'){
         recipe.contributor = dataString;
       }
       else if(str === '</category>'){
         recipe.category = dataString;
       }
       else if(str === '</description>'){
         recipe.description = dataString;
       }
       else if(str === '</spices>'){
         recipe.spices = dataString;
       }
       else if(str === '</source>'){
         recipe.source = dataString;
       }
       else if(str === '</rating>'){
         recipe.rating = dataString;
       }
       else if(str === '</ingredients>'){
         recipe.ingredients = dataString;
       }
       else if(str === '</directions>'){
         recipe.directions = dataString;
       }
       else if(str === '</recipe>'){
         recipes.push(recipe);
         recipe = {};
       }
           openingTag = '';      
       //console.log("LINE " + str)
    }
    else {
      dataString += (" " + str);
    }

        if (last) {
       //done reading file
           console.log("DONE");
       console.log(JSON.stringify(recipes, null, 4));
           writeRecipesToFile(recipes);
           writeRecipesToDatabase(recipes);      
       console.log('Number of Recipes: ' + recipes.length);
           return false; // stop reading 
           } 
}); 
