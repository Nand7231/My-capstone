
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

let recipeContainer = document.getElementById('recipe-container')
const submitForm = document.getElementById('recipeForm')

const headers = {
'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/"

async function getRecipes(userId) {
    await fetch(`${baseUrl}recipes/user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createRecipeCards(data))
        .catch(err => console.error(err))
}
const createRecipeCards = (array) => {
    recipeContainer.innerHTML = ''
    array.forEach(obj => {
        let recipeCard = document.createElement("div")
        recipeCard.classList.add("small")
        recipeCard.innerHTML = `
            <div class="small">
                  <article class="recipe">
                      <div class="recipe-content">
                          <h1 class="recipe-name">${obj.recipeName}</h1>
                          <p class="recipe-desc">${obj.recipeDescription}</p>
                          <button class="recipe-delete" type="button" onclick="deleteRecipe(${obj.id})">Delete</button>

                      </div>
                  </article>

              </div>
        `
        recipeContainer.append(recipeCard);
    })
}
async function deleteRecipe(recipeId) {
await fetch (`${baseUrl}recipes/${recipeId}`, {
method: "DELETE",
headers: headers
})
.catch(err => console.error(err))
return getRecipes(userId);
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

function goToHomePage() {
window.location.replace("http://localhost:8080/home.html");
}

const handleSubmit = async (e) => {
e.preventDefault()
let bodyObj = {
recipeName: document.getElementById("recipeName").value,
recipeDescription: document.getElementById("recipeDescription").value
}
await addRecipe(bodyObj);
document.getElementById("recipeName").value = ''
document.getElementById("recipeDescription").value = ''
}

async function addRecipe(obj) {
const response = await fetch(`${baseUrl}recipes/users/${userId}`, {
method:"POST",
body: JSON.stringify(obj),
headers: headers
})
.catch(err => console.error(err.message))
if (response.status == 200) {
return getRecipes(userId);
}
}
getRecipes(userId);
submitForm.addEventListener("submit", handleSubmit)