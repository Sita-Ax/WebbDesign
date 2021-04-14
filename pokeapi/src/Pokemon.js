
export async function getAllPokemon(url){    
    const resp = await fetch(url);
    return resp.json();
} //fetch the data and then get all pokemon

export async function getPokemon(url) {
    const resp = await fetch(url);
    return resp.json();    
}