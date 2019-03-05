var fs = require('fs');

function rearrangelyrics (variable){
fs.readFile('songs/' + variable, (err, data) => {
  if(err) throw err;
  var splitarray = data.toString().split("\n");
  var wordarray = [];
  var notes   = "";
  var wordlyrics  = "";
  for(var i=0; i<splitarray.length; i++) {
    for(var j=0; j<splitarray[i].length; j++) {
      if(splitarray[i][j] == "[") {
        j += 1;
        while(splitarray[i][j] != "]") {
          notes += splitarray[i][j];
          j += 1;
        }
      }
      else {
        notes  += " ";
        wordlyrics += splitarray[i][j];
      }
    }
    wordarray.push(notes);
    wordarray.push(wordlyrics);
    notes  = "";
    wordlyrics = "";
  }

  var y = 20
  var x = 10;
  var songtitle = variable.substring(0, variable.length - 4);

  for (var i = 0; i < wordarray.length; i++){
    var samplearray = wordarray[i].split(" ");
    for (var j = 0; j < samplearray.length; j++){
        songs[songtitle].push({word: samplearray[j], x, y});
        if(samplearray[j].length == 0){
          x += 0.80*15; 
        }
        else{
          x += samplearray[j].length*19; 
        }
    }
    x = 10;
    y += 30;
  }
})};

var peacefulEasyFeeling = [];
rearrangelyrics('Peaceful Easy Feeling.txt');

var sisterGoldenHair = [];
rearrangelyrics('Sister Golden Hair.txt');

var brownEyedGirl = [];
rearrangelyrics('Brown Eyed Girl.txt');


var songs = {"Peaceful Easy Feeling" : peacefulEasyFeeling,
             "Sister Golden Hair" : sisterGoldenHair,
             "Brown Eyed Girl" : brownEyedGirl
			 };

//Server Code
var http = require('http'); //need to http
var fs = require('fs'); //need to read static files
var url = require('url');  //to parse url strings

var counter = 1000; //to count invocations of function(req,res)


var ROOT_DIR = 'html'; //dir to serve static files from

var MIME_TYPES = {
    'css': 'text/css',
    'gif': 'image/gif',
    'htm': 'text/html',
    'html': 'text/html',
    'ico': 'image/x-icon',
    'jpeg': 'image/jpeg',
    'jpg': 'image/jpeg',
    'js': 'text/javascript', //should really be application/javascript
    'json': 'application/json',
    'png': 'image/png',
    'txt': 'text/plain'
};

var get_mime = function(filename) {
    var ext, type;
    for (ext in MIME_TYPES) {
        type = MIME_TYPES[ext];
        if (filename.indexOf(ext, filename.length - ext.length) !== -1) {
            return type;
        }
    }
    return MIME_TYPES['txt'];
};

http.createServer(function (request,response){
     var urlObj = url.parse(request.url, true, false);
     console.log('\n============================');
	 console.log("PATHNAME: " + urlObj.pathname);
     console.log("REQUEST: " + ROOT_DIR + urlObj.pathname);
     console.log("METHOD: " + request.method);
	 
     var receivedData = '';

     //attached event handlers to collect the message data
     request.on('data', function(chunk) {
        receivedData += chunk;
     });
	 
	 //event handler for the end of the message
     request.on('end', function(){
        console.log('type: ', typeof receivedData);
		
		//if it is a POST request then echo back the data.
		if(request.method == "POST"){
		   var returnObj = {};
       var dataObj = JSON.parse(receivedData);
           console.log('received data object: ', dataObj);
           console.log('type: ', typeof dataObj);
		   
      if(undefined == songs[dataObj.text]){
        returnObj.text = 'NOT FOUND: ' + dataObj.text;
        returnObj.wordArray = " ";
      }
      else{
        returnObj.text = 'FOUND: ' + dataObj.text;
        returnObj.wordArray = songs[dataObj.text]
      }
      
      if(dataObj.lyrics){
        fs.writeFile('songs/' + dataObj.nameOfSong + ".txt", dataObj.lyrics, function(err) {
          if(err) {
            return console.log(err);
          }
        });  
      }
		   console.log("USER REQUEST: " + dataObj.text );		   
		   response.writeHead(200, {'Content-Type': MIME_TYPES[dataObj.text]});
       response.end(JSON.stringify(returnObj));	   
		   //object to return to client
       //does not work with application/json MIME
       //send just the JSON object
		}
     });
	 
     if(request.method == "GET"){
	 //handle GET requests as static file requests
	 var filePath = ROOT_DIR + urlObj.pathname;
	 if(urlObj.pathname === '/') filePath = ROOT_DIR + '/index.html';

     fs.readFile(filePath, function(err,data){
       if(err){
		  //report error to console
          console.log('ERROR: ' + JSON.stringify(err));
		  //respond with not found 404 to client
          response.writeHead(404);
          response.end(JSON.stringify(err));
          return;
         }
         response.writeHead(200, {'Content-Type': get_mime(filePath)});
         response.end(data);
       });
	 }
 }).listen(3000);

console.log('Server Running at http://127.0.0.1:3000  CNTL-C to quit');