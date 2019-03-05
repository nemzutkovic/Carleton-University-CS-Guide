
/*
Demonstration code to access MongoDB database from Node.js application:

Prerequisites:

1)You must have mongodb server running. It should have a database called "dbSongs" which contains a collection named "Songs".

2)You must have installed the npm module: mongodb (in the dependencies
section of the package.json file) 
by executing the command:

npm install
*/


const MongoClient = require('mongodb').MongoClient
const DB_PATH = 'mongodb://localhost:27017/dbSongs'


MongoClient.connect(DB_PATH, function(err, db){
   if(err) console.log(`FAILED TO CONNECTED TO: ${DB_PATH}`);
   else{
      console.log(`CONNECTED TO: ${DB_PATH}`);
      db.collection("Songs", function(err, collection){
        var cursor = collection.find();
        cursor.each(function(err,document){
           console.log(document);
           if(document == null) db.close();
        });
      });
   }   
});

