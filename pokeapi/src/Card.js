import React from 'react';

function Card({ pokemon, removePokemon, goToPokemon }) {
    //this is a function that make the card 'turn' and show the back view of the card you choose
    //all the data is send whit this to the 'backview'
    const clickHandler = () => {
        const PokemonInfo = pokemon
        goToPokemon(PokemonInfo);
    }
    
    //this is the remove button that will take away the pokemon you choose
    const removeHandler = () => {
        const remPokemon = pokemon
        removePokemon(remPokemon);
    }
    
    //here is the components create so you can see the picture an the name of all pokemonInfo
    //is´s also the button to revome the card
    return (
        <div className = 'Card'>
            <div className = 'Card__img'>
            <img src = {pokemon.sprites.front_default} onClick={clickHandler} alt= ''/>       
            </div>
            <div className='Card__Name'>{pokemon.name}</div>
            <button onClick={removeHandler} className='removeBtn'>Remove</button>
        </div>
    );
}

export default Card;
