package juego.ui;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT; 
import juego.otro.*;
//Clase intermedia entre los subpaneles y la ventana, dibuja la información de los subpaneles
public class GamePanel extends JPanel{
	
	private KeyboardControl keyboardControl;
	private StatsPanel statsPanel=new StatsPanel();
	private PlayPanel playPanel=new PlayPanel();
	private Protagonista protagonista;
	List <Personaje> actores = new ArrayList<Personaje>();
	
	public GamePanel(){
		this.setRequestFocusEnabled(true);
		this.setSize(getWidth(),getHeight());
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		
		//Añade el panel de estadísticas y establece su localización
		this.add(statsPanel);
		statsPanel.setLocation(0, 0);
		
		//Añade el panel del juego y establece su localización (será bajo las estadísticas)
		this.add(playPanel);
		playPanel.setLocation(0, StatsPanel.STATS_HEIGHT);
		
		
		keyboardControl=new KeyboardControl();
		this.addKeyListener(keyboardControl);
	}
	
	//Llama a la función de paintComponent de PlayPanel
	public void repaintGame(){
		playPanel.repaint();
		statsPanel.repaint();
		
	}
	
	public void addProtagonista(Protagonista protagonista) {
		this.protagonista=protagonista;
		playPanel.addProtagonista(this.protagonista);
	}

	public void addActores(List<Personaje> actores) {
		this.actores=actores;
		playPanel.addActores(this.actores);
		statsPanel.addActores(this.actores);
	

	}

	
}