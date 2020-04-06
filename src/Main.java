import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GameArea gameBoard = new GameArea();
				gameBoard.setAreaOptions();
				System.out.print("Game Board options set.\n");
				gameBoard.createPokemonTiles();
				System.out.print("Game starts!\n");
			}
		});
	}
}

// compilation:
// javac GameArea.java TilesPanel.java PokemonTile.java Main.java