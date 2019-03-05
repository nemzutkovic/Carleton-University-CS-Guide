
/*
This example requires that your install the 'colour' module using command:
npn install colour

This example illustrates that it is fundamentally unsafe in javascript
to use a FOR-EACH loop to loop through the characters of a string.
It is also unsage to use a FOR-EACH loop to  loop through the elements
of a javascript array.

In both cases always use a for(let i=0; i<x.length; i++){...}
*/


var fs = require('fs'); 
var colour = require('colour');  //npm install colour

var str = "One Day My Prince Will Come";
var testStr1 = "";
var testStr2 = "";

//Using For-Each Loop
for(c in str) {testStr1 += c};

//Using For Loop
for(var i = 0; i<str.length; i++) {testStr2 += str[i];}

console.log(testStr1.yellow);
console.log("");
console.log(testStr2.green);

