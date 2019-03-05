var fs = require('fs');
fs.readFile('songs/sister_golden_hair.txt', (err, data) => {
  if(err) throw err;
  var array = data.toString().split("\n");

  var newText = [];
  var notes   = "";
  var lyrics  = "";

  for(var i=0; i<array.length; i++) {
    for(var j=0; j<array[i].length; j++) {
      if(array[i][j] == "[") {
        j += 1;
        while(array[i][j] != "]") {
          notes += array[i][j];
          j += 1;
        }
      }
      else {
        notes  += " ";
        lyrics += array[i][j];
      }
    }
    newText.push(notes);
    newText.push(lyrics);
    notes  = "";
    lyrics = "";
  }

  for (var i = 0; i < newText.length; i++) {
    console.log(newText[i]);
  }
  
});