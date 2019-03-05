// Nemanja Zutkovic 101085982
// Jeremy Kielbiski 100981162

var pingpong = {x: 20, 
				y: 30, 
				xDirection: 1, //+1 for leftwards, -1 for rightwards
				yDirection: 1, //+1 for downwards, -1 for upwards
				Width: 15, //will be updated when drawn
				Height: 15}; //assumed height based on drawing point size
		
var playerOne = {x: 100,
                 y: 450,
				 width: 100,
				 height: 10};

var playerTwo = {x: 550,
				 y: 50,
				 width: 100,
				 height: 10};

var title = {word: "CLASSIC PING PONG", 
             x: 100, 
			 y: 25};

var p1 = {word: "P1", 
          x: 10, 
		  y: 22};

var p2 = {word: "P2", 
          x: 10, 
		  y: 22};

var p1score = {score: 0, 
               x: 130, 
	           y: 22};

var p2score = {score: 0, 
          	   x: 130, 
		       y: 22};

var p1wins = {word: "PLAYER 1 WINS", 
              x: 100, 
			  y: 25};

var p2wins = {word: "PLAYER 2 WINS", 
              x: 100, 
			  y: 25};

var wayPoints = []; //locations where players have been		
var timer;
var pollingTimer; //timer to poll server for location updates
var canvas = document.getElementById('canvas1'); //gameboard canvas
var blankCanvas = document.getElementById('emptycanvas'); //title canvas
var p1canvas = document.getElementById('canvas2'); //playerone canvas
var p2canvas = document.getElementById('canvas3'); //playertwo canvas

// draw CLASSIC PING PONG title in empty box
var titlecontext = blankCanvas.getContext('2d');
titlecontext.font = '20pt Arial';
titlecontext.fillStyle = 'black';
titlecontext.fillText(title.word, title.x, title.y);

// draw P1 in player one box
var p1context = p1canvas.getContext('2d');
p1context.font = '14pt Arial';
p1context.fillStyle = 'blue';
p1context.fillText(p1.word, p1.x, p1.y);

// draw P2 in player two box
var p2context = p2canvas.getContext('2d');
p2context.font = '14pt Arial';
p2context.fillStyle = 'red';
p2context.fillText(p2.word, p2.x, p2.y);

// Set player scores to 0
p1context.fillStyle = 'black';
p1context.fillText(p1score.score, p1score.x, p1score.y);
p2context.fillStyle = 'black';
p2context.fillText(p2score.score, p2score.x, p2score.y);

var drawCanvas = function(){
    var context = canvas.getContext('2d');
    context.fillStyle = 'white';
    context.fillRect(0,0,canvas.width,canvas.height); //erase canvas
    //draw playerTwo paddle
    context.fillStyle = 'red';
	context.fillRect(playerTwo.x,
	                 playerTwo.y,
					 playerTwo.width,
					 playerTwo.height);
	//draw playerTwo paddle way points
	for(i in wayPoints){
		context.strokeRect(wayPoints[i].x,
		             	   wayPoints[i].y,
					 	   playerTwo.width,
					 	   playerTwo.height);
	}
    //draw playerOne paddle
    context.fillStyle = 'blue';
	context.fillRect(playerOne.x,
	                 playerOne.y,
					 playerOne.width,
					 playerOne.height);
	//draw playerOne paddle way points
	for(i in wayPoints){
		context.strokeRect(wayPoints[i].x,
		             	   wayPoints[i].y,
					 	   playerOne.width,
					 	   playerOne.height);
	}

	//draw ping pong
	context.fillStyle = 'black';
	context.fillRect(pingpong.x,
					 pingpong.y,
					 pingpong.Width,
					 pingpong.Height);
	//draw ping pong way points
	for(i in wayPoints){
	context.strokeRect(wayPoints[i].x,
		               wayPoints[i].y,
					   pingpong.width,
					   pingpong.height);
	}
	
}

var displayScore = function(){
	// display P1/P2 score
	p1context.fillStyle = 'white';
	p1context.fillRect(0,0,p1canvas.width,p1canvas.height); //erase canvas
	p1context.fillStyle = 'blue';
	p1context.fillText(p1.word, p1.x, p1.y);
	p1context.fillStyle = 'black';
	p1context.fillText(p1score.score, p1score.x, p1score.y);
	p2context.fillStyle = 'white';
	p2context.fillRect(0,0,p2canvas.width,p2canvas.height); //erase canvas
	p2context.fillStyle = 'red';
	p2context.fillText(p2.word, p2.x, p2.y);
	p2context.fillStyle = 'black';
	p2context.fillText(p2score.score, p2score.x, p2score.y);
}

var checkWin = function(){
	if(p1score.score == 5){
		alert("P1 WINS");
		p1score.score = 0;
		p2score.score = 0;
	}
	if(p2score.score == 5){
		alert("P2 WINS");
		p1score.score = 0;
		p2score.score = 0;
	}
}

function handleTimer(){
	// moves the ping pong
	pingpong.x = (pingpong.x + 5*pingpong.xDirection);
	pingpong.y = (pingpong.y + 5*pingpong.yDirection);

	// makes sure the ping pong bounces off the canvas walls and if not, points are lost
	if(pingpong.x + pingpong.Width > canvas.width){ pingpong.xDirection = -1 };
	if(pingpong.x < 0){ pingpong.xDirection = 1 };
	if(pingpong.y + pingpong.Height > canvas.height){ pingpong.yDirection = -1; }
	if(pingpong.y < 0){ pingpong.yDirection = 1; }
	// if the ping pong hits the wall behind player 1, player 2 gains 1 point
	if((pingpong.y + pingpong.Height) > canvas.height){
		p2score.score += 1;
		displayScore()
	}
	// if the ping pong hits the wall behind player 2, player 1 gains 1 point
	if(pingpong.y < 0){
		p1score.score += 1;
		displayScore()
	}
	// makes sure the ping pong bounces off the player paddles
	if(pingpong.yDirection != -1 && pingpong.x >= playerOne.x && pingpong.x <= (playerOne.x + playerOne.width) && pingpong.y == playerOne.y - playerOne.height){ 
		pingpong.yDirection = -1;	
	}
	if(pingpong.yDirection != 1 && pingpong.x >= playerTwo.x && pingpong.x <= (playerTwo.x + playerTwo.width) && pingpong.y == (playerTwo.y + playerTwo.height)){ 
		pingpong.yDirection = 1;
	}
	drawCanvas()
	checkWin()
}

function pollingTimerHandler() {
  var dataObj = { x: -1, y: -1 }; //used by server to react as poll
  //create a JSON string representation of the data object
  var jsonString = JSON.stringify(dataObj);
  //Poll the server for the location of player one
  $.post("positionData", jsonString, function(data, status) {
    //var locationData = JSON.parse(data);
    var locationData = data;
    playerOne.x = locationData.x;
    playerOne.y = locationData.y;

  });
}

//KEY CODES
var right = 39;
var left = 37;
var A = 65;
var D = 68;

// Allows player to begin the game.
function playerChoice () {
	var userText = $('#userTextField').val(); //get text from user text input field
	if(userText && userText == 'blue'){
	}
	if(userText && userText == 'red'){
	}
	$('#userTextField').val(''); // empties text field
}

function handleKeyDown(e){
	var dXY = 20; //amount to move in both X and Y direction
	if(e.which == right && playerOne.x + playerOne.width + dXY <= canvas.width){ playerOne.x += dXY; } //right arrow
	if(e.which == left && playerOne.x >= dXY) { playerOne.x -= dXY; }  //left arrow
	if(e.which == D && playerTwo.x + playerTwo.width + dXY <= canvas.width) { playerTwo.x += dXY; }
	if(e.which == A && playerTwo.x >= dXY) { playerTwo.x -= dXY; }

  //upate server with position data
  //may be too much traffic?
  var dataObj = { x: playerOne.x, y: playerOne.y};
  //create a JSON string representation of the data object
  var jsonString = JSON.stringify(dataObj);
  //update the server with a new location of player one
  $.post("positionData", jsonString, function(data, status) {
    //do nothing
  });
}

function handleKeyUp(e){
  var dataObj = { x: playerOne.x, y: playerOne.y };
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

document.addEventListener("DOMContentLoaded", function(){
	//This is called after the broswer has loaded the web page
	//add key handler for the document as a whole, not separate elements.	
	document.addEventListener("keydown", handleKeyDown);
	document.addEventListener("keyup", handleKeyUp);
	timer = setInterval(handleTimer, 30);  //tenth of a second
    pollingTimer = setInterval(pollingTimerHandler, 1); //quarter of a second
	drawCanvas();
});