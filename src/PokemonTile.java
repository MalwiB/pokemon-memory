import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PokemonTile extends JButton {
	ImageIcon hidden;
	ImageIcon pokemon;
	Image pokemonImage;
	String pokemonCodeJPEG;
	boolean iconState = false; // false- hidden, true-pokemon
	
	int tileNumber;
	int pokemonCode;
	
	boolean matched = false;

	
	PokemonTile(int a_tileNumber, int a_pokemonCode) {
		pokemonCode = a_pokemonCode;
		tileNumber = a_tileNumber;
		hidden = new ImageIcon("images/hidden.png");
		setHidden();
		pokemonCodeJPEG = "images/" + String.valueOf(pokemonCode) + ".jpg";
		pokemon = new ImageIcon(pokemonCodeJPEG);
		pokemonImage = pokemon.getImage().getScaledInstance(250, 140, 0);
		pokemon.setImage(pokemonImage);
	}
	
	void setPokemon() {
		setIcon(pokemon);
		iconState = true;
	}
	
	void setHidden() {
		setIcon(hidden);
		iconState = false;
	}
	
	void setMatched() {
		matched = true;
	}
	
	void lockTile() {
		this.setEnabled(false);
		if (iconState == true)
			this.setDisabledIcon(pokemon);
		else
			this.setDisabledIcon(hidden);
	}
	
	void unlockTile() {
		this.setEnabled(true);
		if (iconState == true)
			this.setDisabledIcon(pokemon);
		else
			this.setDisabledIcon(hidden);
	}
	
	
	
	
}
