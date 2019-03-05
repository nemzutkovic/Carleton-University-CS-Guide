/*
Hello World example to serving string to client
*/
//To test use browser to view http://localhost:3000
//Cntl+C to stop server

var http = require('http');
var counter = 1000; 

http.createServer(function (request,response){
   //respond to client
   response.writeHead(200, {'Content-Type': 'text/plain'});
   response.write('Hello\n');
   var urlString = request.url;
   var url = urlString.split("&");
   var newurl = [];

   for (var i = 0; i < url.length; i++){
   	newurl[i] = url[i].split("=");
   }
   response.write(newurl[0][1] + "\n");

   //end HTTP response and provide final data to send
   response.end("["+ counter++ + "]\n");
}).listen(3000);
console.log('Server Running at http://127.0.0.1:3000  CNTL-C to quit');
console.log('To test with browser visit: http://localhost:3000');