import React, { useState, useEffect } from 'react';
import './style.css';
import { getPokemon, getAllPokemon } from './Pokemon';
import Card from './Card';
import Card2 from './Card2';

function App() {

  //this is to put in the data in an empty array use the state when ju use this to get some data
  const [pokemonData, setPokemonData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [view, setView] = useState('');
    //an object to get the pokemon that will be clicked on
  const [specificPokemon, setSpecificPokemon] = useState({});

  //a function that take in a input it will call when we mount and uppdate or after every renderingen
  useEffect(() => {
    const fetchData = async () => {
      const resp = await getAllPokemon('https://pokeapi.co/api/v2/pokemon?limit=100');
      const pokemon = await loadingPokemon(resp.results);
      setLoading(false);
    }
    fetchData();
  }, []);

  //all the data we need whit a callback
  const loadingPokemon = async (data) => {
      const pokemonInfo = await Promise.all(data.map(async pokemon => {
      const pokemonRec = await getPokemon(pokemon.url);
      return pokemonRec;
    }));
    //take the array and set the data to every one
    setPokemonData(pokemonInfo);
  };
  
  //take away the pokemon when the button is clicked on
  const removePokemon = (remPokemon) => {
    const newPokemon = pokemonData.filter(pokemon => pokemon !== remPokemon)
    setPokemonData(newPokemon);
  }
  
  //pokemon that i clicked on send back the pokemin info to the other class
  const goToPokemon = (PokemonInfo) => {
    setSpecificPokemon(PokemonInfo)
    setView('card2');
  }

  //show the view you want to see
  switch (view) {
    case 'card2':
      return (
        <div className='flex-container'>
          <Card2 specificPokemon={specificPokemon} setView={setView} />
        </div>
      )
    default:
      return (
        //this is child that change view mm
        <div>
          <div class="index-module__logo_container--sUGNH"><img alt="PokéAPI" src="https://raw.githubusercontent.com/PokeAPI/media/master/logo/pokeapi_256.png" /></div>
          {loading ? (
            <h1>Loading...</h1>) : (
            <>
              <div className='flex-container'>
                {pokemonData.map((pokemon, i) => {
                  return <Card key={i} pokemon={pokemon} removePokemon={removePokemon} goToPokemon={goToPokemon}  />;
                })}
              </div>
            </>
          )}
        </div>
      );
  }
}

export default App;

