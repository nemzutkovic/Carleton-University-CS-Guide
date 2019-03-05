// Nemanja Zutkovic 101085982
// Jeremy Kielbiski 100981162

var http = require("http"); //need to http
var fs = require("fs"); //need to read static files
var url = require("url"); //to parse url strings

//server maintained location player one paddle, player two paddle and ping pong location
var playerOneLocation = { x: 100, y: 450 }; 
var playerTwoLocation = { x: 550, y: 50 };
var pingPongLocation = { x: 20, y: 30 };

var userpick 

var ROOT_DIR = "html"; //dir to serve static files from

var MIME_TYPES = {
  css: "text/css",
  html: "text/html",
  js: "application/javascript",
  json: "application/json",
};

var get_mime = function(filename) {
  var ext, type;
  for (ext in MIME_TYPES) {
    type = MIME_TYPES[ext];
    if (filename.indexOf(ext, filename.length - ext.length) !== -1) {
      return type;
    }
  }
  return MIME_TYPES["txt"];
};

http.createServer(function(request, response) {
    var urlObj = url.parse(request.url, true, false);
    var receivedData = "";

    //attached event handlers to collect the message data
    request.on("data", function(chunk) {
      receivedData += chunk;
    });

    //event handler for the end of the message
    request.on("end", function() {
      //if it is a POST request then echo back the data.

      if (request.method == "POST") {
        var dataObj = JSON.parse(receivedData);  
        if (dataObj.x >= 0 && dataObj.y >= 0) {
          //Here a client is providing a new location for player one
          //capture location of player one from client
          playerOneLocation = JSON.parse(receivedData);
        }

        //echo back the location of player one
        //sent the POST message
        response.writeHead(200, { "Content-Type": MIME_TYPES["json"] });
        response.end(JSON.stringify(playerOneLocation)); //send just the JSON object
      }

      if (request.method == "GET") {
        //handle GET requests as static file requests
        fs.readFile(ROOT_DIR + urlObj.pathname, function(err, data) {
          if (err) {
            //report error to console
            //respond with not found 404 to client
            response.writeHead(404);
            response.end(JSON.stringify(err));
            return;
          }
          response.writeHead(200, {
            "Content-Type": get_mime(urlObj.pathname)
          });
          response.end(data);
        });
      }
    });
  })
  .listen(3000);
console.log("Server Running at http://127.0.0.1:3000  CNTL-C to quit");