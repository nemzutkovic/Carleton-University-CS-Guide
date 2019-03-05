    var words = [];
	words.push({word: "I", x:50, y:50});
	words.push({word: "like", x:70, y:50});
	words.push({word: "the", x:120, y:50});
	words.push({word: "way", x:170, y:50});
	words.push({word: "your", x:230, y:50});
	words.push({word: "sparkling", x:300, y:50});
	words.push({word: "earrings", x:430, y:50});
	words.push({word: "lay", x:540, y:50});

var movingString = {x: 100, 
					y:100, 
					xDirection: 1, //+1 for leftwards, -1 for rightwards
					yDirection: 1, //+1 for downwards, -1 for upwards
					stringWidth: 50, //will be updated when drawn
					stringHeight: 24}; //assumed height based on drawing point size

//indended for keyboard control					
var movingBox = {x: 50,
                 y: 50,
				 width: 100,
				 height: 20};
				 
var wayPoints = []; //locations where the moving box has been
					
var timer;

var wordBeingMoved;

var deltaX, deltaY; //location where mouse is pressed
var canvas = document.getElementById('canvas1'); //our drawing canvas

function getWordAtLocation(aCanvasX, aCanvasY){
	
	  //locate the word near aCanvasX,aCanvasY
	  //Just use crude region for now.
	  //should be improved to using lenght of word etc.
	  
	  //note you will have to click near the start of the word
	  //as it is implemented now
	  for(var i=0; i<words.length; i++){
		 if(Math.abs(words[i].x - aCanvasX) < 20 && 
		    Math.abs(words[i].y - aCanvasY) < 20) return words[i];
	  }
	  return null;
    }

var drawCanvas = function(){

    var context = canvas.getContext('2d');
	
    context.fillStyle = 'white';
    context.fillRect(0,0,canvas.width,canvas.height); //erase canvas
   
    context.font = '20pt Arial';
    context.fillStyle = 'cornflowerblue';
    context.strokeStyle = 'blue';

    for(var i=0; i<words.length; i++){  //note i declared as var
		    
			var data = words[i];
			context.fillText(data.word, data.x, data.y);
            context.strokeText(data.word, data.x, data.y);
		
	}

    movingString.stringWidth = context.measureText(	movingString.word).width;
	//console.log(movingString.stringWidth);
    context.fillText(movingString.word, movingString.x, movingString.y);
	
    //draw moving box
	context.fillRect(movingBox.x,
	                 movingBox.y,
					 movingBox.width,
					 movingBox.height);
	
	//draw moving box way points
	for(i in wayPoints){
		context.strokeRect(wayPoints[i].x,
		             wayPoints[i].y,
					 movingBox.width,
					 movingBox.height);
	}

}

function handleMouseDown(e){
	
	//get mouse location relative to canvas top left
	var rect = canvas.getBoundingClientRect();
    //var canvasX = e.clientX - rect.left;
    //var canvasY = e.clientY - rect.top;
    var canvasX = e.pageX - rect.left; //use jQuery event object pageX and pageY
    var canvasY = e.pageY - rect.top;
	console.log("mouse down:" + canvasX + ", " + canvasY);
	
	wordBeingMoved = getWordAtLocation(canvasX, canvasY);
	//console.log(wordBeingMoved.word);
	if(wordBeingMoved != null ){
	   deltaX = wordBeingMoved.x - canvasX; 
	   deltaY = wordBeingMoved.y - canvasY;
	
	document.addEventListener("mousemove", handleMouseMove, true);
    document.addEventListener("mouseup", handleMouseUp, true);
	   
	}

    // Stop propagation of the event and stop any default 
    //  browser action

    e.stopPropagation();
    e.preventDefault();
	
	drawCanvas();
	}
	
function handleTimer(){
	movingString.x = (movingString.x + 5*movingString.xDirection); 
	movingString.y = (movingString.y + 5*movingString.yDirection); 
	
	//keep inbounds of canvas	
	if(movingString.x + movingString.stringWidth > canvas.width) movingString.xDirection = -1;
	if(movingString.x < 0) movingString.xDirection = 1;
	if(movingString.y > canvas.height) movingString.yDirection = -1;
	if(movingString.y - movingString.stringHeight < 0) movingString.yDirection = 1;
	
	drawCanvas()
}

    //KEY CODES - should clean up these hard coded key codes
	var RIGHT_ARROW = 39;
	var LEFT_ARROW = 37;

function handleKeyDown(e){
	
	console.log("keydown code = " + e.which );
		
	var dXY = 5; //amount to move in both X and Y direction
	if(e.which == RIGHT_ARROW && movingBox.x + movingBox.width + dXY <= canvas.width) 
	   movingBox.x += dXY;  //right arrow
	if(e.which == LEFT_ARROW && movingBox.x >= dXY) 
	   movingBox.x -= dXY;  //left arrow
	
    var keyCode = e.which;

}

function handleKeyUp(e){
	console.log("key UP: " + e.which);
	if(e.which == RIGHT_ARROW | e.which == LEFT_ARROW){
	var dataObj = {x: movingBox.x, y: movingBox.y}; 
	//create a JSON string representation of the data object
	var jsonString = JSON.stringify(dataObj);

	var xhr = new XMLHttpRequest();
    xhr.open('POST', 'positionData');
    xhr.withCredientials = true;
    xhr.setRequestHeader('Content-Type', 'text/plain');
    xhr.send(userRequestJSON);

    xhr.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		wayPoints.push(JSON.parse(this.responseText).text);	
    	}
    }
	}
	
	if(e.which == ENTER){		  
	   handleSubmitButton(); //treat ENTER key like you would a submit
	   document.getElementById('userTextField').value = ' '; //clear the user text field
	}

	e.stopPropagation();
    e.preventDefault();

}

function handleSubmitButton () {
   
    var userText = document.getElementById('userTextField').value; //get text from user text input field
	if(userText && userText != ''){
	   var userRequestObj = {text: userText};
       var userRequestJSON = JSON.stringify(userRequestObj);
	   document.getElementById('userTextField').value = ' '; //clear the user text field

       var xhr = new XMLHttpRequest();
       xhr.open('POST', 'userText');
       xhr.withCredientials = true;
       xhr.setRequestHeader('Content-Type', 'text/plain');
       xhr.send(userRequestJSON);

       xhr.onreadystatechange = function() {
       		if (this.readyState == 4 && this.status == 200) {
	       		console.log(this);
	       		movingString.word = JSON.parse(this.responseText).text;
	       		if(JSON.parse(this.responseText).wordArray) words = JSON.parse(this.responseText).wordArray;
       		}
       };
	}							
}

document.addEventListener("DOMContentLoaded", function(){
	//This is called after the broswer has loaded the web page
	
	//add mouse down listener to our canvas object
	document.getElementById('canvas1').addEventListener("mousedown", handleMouseDown);
	
	//add key handler for the document as a whole, not separate elements.	
	document.addEventListener("keydown", handleKeyDown);
	document.addEventListener("keyup", handleKeyUp);
		
	timer = setInterval(handleTimer, 100); 
    //timer.clearInterval(); //to stop
	
	drawCanvas();
});