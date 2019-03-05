/*
Example of node-based "static" server created with node.js and
only its internal modules: http, url, and fs.

Here our node server does not try to analyse the url to route the requests, 
it simply servers whatever files that happen to be in the ROOT_DIR directory.
It does however replace a path of '/' with '/index.html'.

Because it is a static server it does not analyse the requested URL.
It simply serves such a file if it exists in the intended directory.

The server analysis the file extension to decide on the appropriate MIME
type to return to the client.
*/

/*
Use browser to view pages at http://localhost:3000/index.html or
other files

*/

//Cntl+C to stop server (in Windows CMD console)

const http = require('http'); //need to http
const fs = require('fs'); //need to read static files
const url = require('url');  //to parse url strings


const ROOT_DIR = 'public'; //dir to serve static files from
const PORT = process.env.PORT || 3000
var MIME_TYPES = {
    'css': 'text/css',
    'gif': 'image/gif',
    'htm': 'text/html',
    'html': 'text/html',
    'ico': 'image/x-icon',
    'jpeg': 'image/jpeg',
    'jpg': 'image/jpeg',
    'js': 'application/javascript',
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

 }).listen(PORT);

console.log('Server Listening on: ' + PORT + ' CNTL-C to quit');