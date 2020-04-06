import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class GameArea extends JFrame {
	Dimension areaSize;
	
	public void setAreaOptions() {
		// Toolkit kit = Toolkit.getDefaultToolkit();
		areaSize = new Dimension(1500, 840);// kit.getScreenSize();
		setSize(areaSize);
		setLocationRelativeTo(null);
		setTitle("MEMORY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void createPokemonTiles () {
		TilesPanel tilesPanel = new TilesPanel();
		System.out.print("Creating pokemon tiles...\n");
		tilesPanel.generateTiles();
		System.out.print("Pokemon tiles generated.\n");
		tilesPanel.layoutTilesPanel();
		add(tilesPanel);
	}
}
