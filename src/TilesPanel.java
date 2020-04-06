import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


public class TilesPanel extends JPanel implements ActionListener {
	static int POKEMON_TILES_NUMBER = 36;
	static int POKEMON_TILES_IN_LINE_NUMBER = (int) Math.sqrt(POKEMON_TILES_NUMBER);
	
	static int[] numbersOfGeneratedPokemons = new int[POKEMON_TILES_NUMBER / 2];
	
	PokemonTile[] tiles = new PokemonTile[POKEMON_TILES_NUMBER];
	
	int numberOfClick = 1;
	
	PokemonTile firstClickedPokemon;
	PokemonTile secondClickedPokemon;
	
	void generateTiles() {
		for(int i=0; i<POKEMON_TILES_NUMBER; i++) {
			int drawnCode = drawPokemonCode();
			generatePokemonTile(i, drawnCode);
		}
	}
	
	int drawPokemonCode() {
		// random number from [0-18) interval
		int pokemonCodesNumber = POKEMON_TILES_NUMBER / 2;
		Random gen = new Random();
		int drawnCode;
		do {
			 drawnCode = gen.nextInt(pokemonCodesNumber);
		    } while(wasCodeDrawn(drawnCode) == true);
		numbersOfGeneratedPokemons[drawnCode]++;
		return drawnCode;
	}
	
	Boolean wasCodeDrawn(int code) {
		if(numbersOfGeneratedPokemons[code] == 2)
			return true;
		else
			return false;
	}
	
	void generatePokemonTile(int i, int drawnCode) {
		tiles[i] = new PokemonTile(i, drawnCode);
		tiles[i].addActionListener(this);
		add(tiles[i]);
	}
	
	void layoutTilesPanel() {
		setLayout(new GridLayout(POKEMON_TILES_IN_LINE_NUMBER, POKEMON_TILES_IN_LINE_NUMBER));
	}
	
	void unlockTiles() {
		for(int i=0; i<POKEMON_TILES_NUMBER; i++) {
			if(tiles[i].matched != true)
				tiles[i].unlockTile();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// when pictures should be visible?
		// 1) when pictures(pokemonCode) are the same, but numbers(tileNumber) differ
		
		// when is there need to hide pictures under tiles?
		// 1) when pictures(pokemonCode) and numbers(tileNumber) differ
		// 2) or when pictures(pokemonCode) are the same and numbers(tileNumber) are the same
		
		PokemonTile currentTile = (PokemonTile) e.getSource();
		
		if(numberOfClick == 1) {
			currentTile.setPokemon();
			currentTile.lockTile();
			firstClickedPokemon = currentTile;
			numberOfClick = 2;
		}
		else if (numberOfClick == 2) {
			currentTile.setPokemon();
			currentTile.lockTile();
			secondClickedPokemon = currentTile;
			
			int firstClickedTileNumber = firstClickedPokemon.tileNumber;
			int secondClickedTileNumber = secondClickedPokemon.tileNumber;
			
			// lock tiles for a moment
			for(int i=0; i<POKEMON_TILES_NUMBER; i++) {
				tiles[i].lockTile();
			}
			
			// if pair not found...
			if(! (firstClickedPokemon.pokemonCode == secondClickedPokemon.pokemonCode && firstClickedTileNumber != secondClickedTileNumber)) {
				Timer timer = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent e ) {
						firstClickedPokemon.setHidden();
						secondClickedPokemon.setHidden();
						unlockTiles();
					}
					} );
					timer.setRepeats(false);
					timer.start();
			}
			// pair found...
			else {
				firstClickedPokemon.setMatched();
				secondClickedPokemon.setMatched();
				unlockTiles();
			}
			numberOfClick = 1;
		}
	}
}
