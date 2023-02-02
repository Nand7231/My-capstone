const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

let recipeContainer = document.getElementById('recipe-container')
const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/"


async function saveRecipe(userId, recipeId) {

await fetch(`${baseUrl}users/${userId}/recipe/${recipeId}`,{
    method:"PUT",
    headers: headers
})
    .catch(err => console.error(err))

//    return getRecipe(recipeId)
}

async function getRecipes(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createRecipeCards(data))
        .catch(err => console.error(err))
}
async function getAllRecipes() {
    await fetch(`${baseUrl}recipes/`, {
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

                          <button class="recipe-save" onclick="saveRecipe(${userId}, ${obj.id})">
                              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="#000000"><path d="M 6.0097656 2 C 4.9143111 2 4.0097656 2.9025988 4.0097656 3.9980469 L 4 22 L 12 19 L 20 22 L 20 20.556641 L 20 4 C 20 2.9069372 19.093063 2 18 2 L 6.0097656 2 z M 6.0097656 4 L 18 4 L 18 19.113281 L 12 16.863281 L 6.0019531 19.113281 L 6.0097656 4 z" fill="currentColor"/></svg>
                              Save
                          </button>

                      </div>
                  </article>

              </div>
        `
        recipeContainer.append(recipeCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}
function goToYourRecipes(){
window.location.replace("http://localhost:8080/recipeList.html")
}
getAllRecipes();

