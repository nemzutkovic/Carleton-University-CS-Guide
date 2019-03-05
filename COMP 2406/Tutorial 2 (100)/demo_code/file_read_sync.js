
/*
Example of a SYNCHRONOUS file read.
the readFileSync() function blocks (waits) and only returns once the file is read.

See
http://nodejs.org/api/fs.html
and more specifically: http://nodejs.org/api/fs.html#fs_fs_readfilesync_filename_options

*/

var fs = require('fs');  
var array = fs.readFileSync('songs/sister_golden_hair.txt','utf8').split("\n");
var result = [];
var note = false;
var endIndex = 0;
var colour = require('colour');

for(var i in array){
  var chords = "";
  var lyrics = "";
  for(var j = 0; j<array[i].length; j++){
    if(array[i][j] == '['){    //if note started
      j++;
      while(array[i][j] != ']'){    //while going through the note, adds note
        chords+= array[i][j];
    lyrics+= " ";
    j++;
      }
      //continue;
    }
    else{
      chords+= " ";
      lyrics+= array[i][j];
    }
  }//now add each line to result
  if(chords.trim()){
    console.log(chords.green);
    result.push(chords);
 }
  console.log(lyrics.yellow);
  result.push(lyrics);
}

 
for(var i in result) { 
  //console.log(array[i]);
  //console.log(result[i]);
} 
