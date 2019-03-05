/*
VERSION OF TUTORIAL 03 DEMO CODE WHERE THE CLIENT-SERVER EXCHANGE
IS USING MIME_TYPE 'application/json'

Here we are prepared to receive a POST message from the client,
and acknowledge that, with a very limited response back to the client
*/

/*
Use browser to view pages at http://localhost:3000/example1.html

When the blue cube is moved with the arrow keys, a POST message will be
sent to the server when the arrow key is released. The POST message will
contain a data string which is the location of the blue cube when the
arrow key was released. The server sends back a JSON string which the client should use
to put down a "waypoint" for where the arrow key was released

Also if the client types in the app text field and presses the "Submit Request" button
a JSON object containing the text field text will be send to this
server in a POST message.

Notice in this code we attach an event listener to the request object 
to receive data that might come in in chunks. When the request end event
is posted we look and see if it is a POST message and if so extract the
data and process it.


*/

//Cntl+C to stop server (in Windows CMD console)

//DATA to be used in a future tutorial exercise.
/*Exercise: if the user types the title of a song that the server has,
  the server should send a JSON object back to the client to replace
  the words array in the client app.
*/
 
//hard coded songs to serve client 
var peacefulEasyFeeling = [];
peacefulEasyFeeling.push({word: "I", x:50, y:50});
peacefulEasyFeeling.push({word: "like", x:70, y:50});
peacefulEasyFeeling.push({word: "the", x:120, y:50});
peacefulEasyFeeling.push({word: "way", x:170, y:50});
peacefulEasyFeeling.push({word: "your", x:230, y:50});
peacefulEasyFeeling.push({word: "sparkling", x:300, y:50});
peacefulEasyFeeling.push({word: "earrings", x:430, y:50});
peacefulEasyFeeling.push({word: "lay", x:540, y:50});

var sisterGoldenHair = [];
sisterGoldenHair.push({word: "Well", x:50, y:50});
sisterGoldenHair.push({word: "I", x:110, y:50});
sisterGoldenHair.push({word: "tried", x:130, y:50});
sisterGoldenHair.push({word: "to", x:190, y:50});
sisterGoldenHair.push({word: "make", x:220, y:50});
sisterGoldenHair.push({word: "it", x:290, y:50});
sisterGoldenHair.push({word: "Sunday", x:320, y:50});

var brownEyedGirl = [];
brownEyedGirl.push({word: "Hey", x:40, y:50});
brownEyedGirl.push({word: "where", x:100, y:50});
brownEyedGirl.push({word: "did", x:180, y:50});
brownEyedGirl.push({word: "we", x:220, y:50});
brownEyedGirl.push({word: "go", x:260, y:50});
brownEyedGirl.push({word: "Days", x:40, y:100});
brownEyedGirl.push({word: "when", x:110, y:100});
brownEyedGirl.push({word: "the", x:190, y:100});
brownEyedGirl.push({word: "rains", x:240, y:100});
brownEyedGirl.push({word: "came", x:320, y:100});

var songs = {"Peaceful Easy Feeling" : peacefulEasyFeeling,
             "Sister Golden Hair" : sisterGoldenHair,
             "Brown Eyed Girl" : brownEyedGirl
			 };


//Server Code
var http = require('http'); //need to http
var fs = require('fs'); //need to read static files
var url = require('url');  //to parse url strings

var counter = 1000;


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

var get_mime = (filename) => {
    var ext, type;
    for (ext in MIME_TYPES) {
        type = MIME_TYPES[ext];
        if (filename.indexOf(ext, filename.length - ext.length) !== -1) {
            return type;
        }
    }
    return MIME_TYPES['txt'];
};

http.createServer( (request, response) => {
     var urlObj = url.parse(request.url, true, false);
     console.log('\n============================');
	 console.log("PATHNAME: " + urlObj.pathname);
     console.log("REQUEST: " + ROOT_DIR + urlObj.pathname);
     console.log("METHOD: " + request.method);
	 
     var receivedData = '';

     //attached event handlers to collect the message data
     request.on('data', (chunk) => {
        receivedData += chunk;
     });
	 
	 //event handler for the end of the message
     request.on('end', () => {
        console.log('received data: ', receivedData);
        console.log('type: ', typeof receivedData);
		
		//if it is a POST request then echo back the data.
		if(request.method == "POST"){
		   var dataObj = JSON.parse(receivedData);
           console.log('received data object: ', dataObj);
           console.log('type: ', typeof dataObj);
		   //Here we can decide how to process the data object and what
		   //object to send back to client.
		   //FOR NOW EITHER JUST PASS BACK AN OBJECT
		   //WITH "text" PROPERTY 
		   
		   //TO DO: return the words array that the client requested
		   //if it exists

		   console.log("USER REQUEST: " + dataObj.text );		   
		   var returnObj = {};
		   returnObj.text = 'NOT FOUND: ' + dataObj.text;
		   for(title in songs) {
		       //console.log(title + " : " + dataObj.text);
		       if (title === dataObj.text){
			      returnObj.text = 'FOUND';
			      returnObj.wordArray = songs[title];
			   }
		   }
		   		   
		   //object to return to client
           response.writeHead(200, {'Content-Type': MIME_TYPES['json']});  
           response.end(JSON.stringify(returnObj)); //send just the JSON object
		}
        if(request.method == "GET"){
	        //handle GET requests as static file requests
	        var filePath = ROOT_DIR + urlObj.pathname;
	        if(urlObj.pathname === '/') filePath = ROOT_DIR + '/index.html';

	    }
     });
 }).listen(3000);

console.log('Server Running at http://127.0.0.1:3000  CNTL-C to quit');