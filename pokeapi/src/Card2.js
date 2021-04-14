import React from 'react';
import './Card2.css';

function Card2({ specificPokemon, setView }) {
    console.log(specificPokemon + '2');
    const backHandler = () => {
        setView('card');
    }
    return (
        <div className='Card'>
            <div className='Card__img'>
                <img src={specificPokemon.sprites} alt='' />
            </div> 
            <div className='Card__types'>{specificPokemon.types.map((type, i) => {
                return (<div className='Card__type' key={i}>{type.type.name}</div>)
            })}
            </div>
            <div className='Card__info'>
                <div className='Card__data Card__data--weight'>
                    <p className='title'>Weight: </p>
                    <p>{specificPokemon.weight}</p>
                </div>
                <div className='Card__data Card__data--height'>
                    <p className='title'>Height: </p>
                    <p>{specificPokemon.height}</p>
                </div>
                <div className='Card__data Card__data--ability'>
                    <p className='title'>Ability: </p>
                    <p>{specificPokemon.abilities.map((ability, i) => {
                        return (<div key={i}>{ability.ability.name}</div>)
                    })}</p>
                </div>
            </div>
            <button onClick={backHandler} className='backBtn'>Go back</button>
        </div>
    );
}

export default Card2;


