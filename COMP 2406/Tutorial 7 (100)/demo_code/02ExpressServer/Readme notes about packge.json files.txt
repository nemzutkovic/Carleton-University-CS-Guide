You can create an initial package.json file by executing:
>npm init
and answering the questions is asks.

Comments: package.json files have no "offical" mechanism for adding comments
because they are just a JSON string.

Here are some notes that I would have preferred to keep within the package.json file


/*
code example using the underscore.js module
which was installed from npm online registry using commandline:

Installing the module Underscore

npm install underscore  //will install the latest version
npm install underscore --save  //will install underscore and update the package.json
                               //file if it exists
npm install  //will install required modules indicated in 
             //dependencies section of the package.json file


Version Numbers of Dependency modules are one of the most important things
in the package.json file.
Version X.Y.Z
X = major version (major version changes are not generally backwards compatible)
Y = minor version (minor version updates are meant to be backwards compatible)
Z = patch version (increments when backwards compatible fix is made)

Desired version is specified as follows:

version     //Must match version exactly
>version    //Must be greater than version
>=version   //etc.
<version
<=version
~version    //latest patch compatible with stated version
^version    //latest minor version compatible with stated version
1.2.x       //1.2.0, 1.2.1, etc., but not 1.3.0
http://...  //See 'URLs as Dependencies' below
*           //Matches any version
""          //(just an empty string) Same as *
version1 - version2   //Same as >=version1 <=version2.
range1 || range2    //Passes if either range1 or range2 are satisfied.
git...      //See 'Git URLs as Dependencies' below
user/repo   //See 'GitHub URLs' below


*/


var _ = require('underscore');

_.each([1,2,3], function(num){
   console.log("underscore.js says: " + (num*10));
});


/*
OUTPUT:
underscore.js says: 10
underscore.js says: 20
underscore.js says: 30
*/

/*Exercise: go to http://npmjs.org and use the search field to see what your can 
learn about the underscore module. It is a "swiss army knife" of useful little ways
to iterate over a list
*/
