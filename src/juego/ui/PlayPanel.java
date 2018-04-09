package juego.ui;
import juego.otro.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


//En esta clase vemos el juego en movimiento, donde se dibujan las cosas

//Donde se dibuja el jugador y el escenario
public class PlayPanel extends JPanel{

	//altura donde se pondrá el sprite del jugador
	public static final int TERRAIN_HEIGHT=192;
	public static final int PLAY_PANEL_HEIGHT=640;
	Protagonista protagonista;
	List <Personaje> actores = new ArrayList<Personaje>();
	List <Escenario> elementos =new ArrayList<Escenario>();
	Image background, personaje;

	public PlayPanel(){
		//establece el tamaño de la altura del panel
		this.setSize(GameFrame.WIDTH, PLAY_PANEL_HEIGHT);
		
		this.setBackground(Color.DARK_GRAY);
		
		this.setLayout(null);
		
		this.setDoubleBuffered(true);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
	
		//para dibujar líneas e imágenes más fluidas
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//temporal: dibujar celdas para distinguirlo del StatsPanel
		for(int i=0; i<20; i++){
			g2.drawLine(0, i*64, GameFrame.WIDTH, i*64);
			g2.drawLine(i*64, 0, i*64, GameFrame.HEIGHT);
		}
	
		
		g2.drawImage(World.CURRENT_BACKGROUND,0,-Escenario.Escenario_SIZE,GameFrame.WIDTH,PLAY_PANEL_HEIGHT, null);
		
			
		for (int i=0; i<elementos.size();i++) {
			g2.drawImage(elementos.get(i).getImage(), elementos.get(i).getCol()*Escenario.Escenario_SIZE, elementos.get(i).getRow()*Escenario.Escenario_SIZE, null);
		}
				for(int i=0; i<World.ROWS; i++){
					for(int j=0; j<World.COLS; j++){
						if(World.EscenariodMap[i][j]!=null){
							
							g2.drawImage(World.EscenariodMap[i][j].getImage(), j*Escenario.Escenario_SIZE, i*Escenario.Escenario_SIZE, null);
						
						}
					}
				}
		try {
			protagonista.paint(g2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for(int i=0; i<actores.size(); i++) {
			actores.get(i).paint(g2);
		}
	
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	public void addProtagonista(Protagonista protagonista) {
		this.protagonista=protagonista;
	//	personaje=protagonista.getImagen();
		//System.out.println("metodo add play panel"+protagonista);
		
	}
	public void addActores(List<Personaje> actores) {
		this.actores=actores;

	}
	
	public void addElementos(List<Escenario> elementos) {
		this.elementos=elementos;

	}

	

}