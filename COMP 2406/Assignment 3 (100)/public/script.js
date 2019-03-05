// Nemanja Zutkovic (101085982)

function getIngredients() {

    let foodName = document.getElementById('ingredient').value
    if(foodName === '') {
        return alert('Please enter an ingredient');
    }

    let foodDiv = document.getElementById('recipe')
    foodDiv.innerHTML = '';

    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) { 
            let response = JSON.parse(xhr.responseText);
            let recipes = JSON.parse(response);

            // implemented an adjustable recipe counter to limit the amount of data recieved by the food2fork query, if too much data is provided (R1.8).
            var recipecounter = 0;

			for (var key in recipes) {
				for(var i in recipes[key]){
                    recipecounter ++;
                    if(recipecounter > 30){
                        break;
                    };

					foodDiv.innerHTML = foodDiv.innerHTML + `
                    <div class="recipe">
					<img src="${recipes[key][i].image_url}"/>
                    <a href=${recipes[key][i].f2f_url}><h1 style="font-size: 100%;">Recipe for ${recipes[key][i].title}</h1></a>
                    </div>
					`
				}
			}
        }
    }

    xhr.open('GET', `/recipes?ingredients=${foodName}`, true)
    xhr.send()
}

document.addEventListener('DOMContentLoaded', function() {
    let foodurl = window.location.href;
    if(foodurl.indexOf("?ingredients=") >= 0){
        let indexstart = foodurl.indexOf("=");
        let foodstring = foodurl.substring(indexstart + 1, foodurl.length);
        document.getElementById('ingredient').value = foodstring;
    }
    else{
     document.getElementById('ingredient').value = 'cake';   
    }
    getIngredients();    
});

//Attach Enter-key Handler
const ENTER=13
document.getElementById("ingredient")
    .addEventListener("keyup", function(event) {
    event.preventDefault();
    if (event.keyCode === ENTER) {
        document.getElementById("submit").click();
    }
});