package juego.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import juego.otro.*;
//Clase para gestionar las estadísticas del jugador


public class StatsPanel extends JPanel{
	public static final int STATS_HEIGHT=40;
	private static final long serialVersionUID = 1L;
	private BufferedImage vida;
	int x=3;
	private static final int HEARTS_X_DISTANCE=60;
	private static final int HEARTS_START_X=84;
	private static final int HEARTS_START_Y=4;
	private static final int HEARTS_SIZE=32;
	List <Personaje> actores = new ArrayList<Personaje>();
		public StatsPanel(){
		this.setSize(GameFrame.WIDTH, STATS_HEIGHT);
		this.setBackground(Color.BLACK);
		this.setLayout(null);
	
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		try {
			vida=ImageIO.read(getClass().getResource("/juego/res/heart.png"));
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.WHITE);
		//para dibujar líneas e imágenes más fluidas
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(this.actores.size()<=0) g2.drawString("HAS GANADO", 600, 20);
		if(Protagonista.getActualY()>GameFrame.HEIGHT-50) {
			g2.drawString("GAME OVER", 600, 20);
		}
		for(int i=0; i<Protagonista.cantidadVida;i++) {
			int j=i+20;
			g2.drawImage(vida,HEARTS_START_X+HEARTS_X_DISTANCE*i,HEARTS_START_Y,HEARTS_SIZE,HEARTS_SIZE,null);
					}
		
		if(Protagonista.cantidadVida<=0) g2.drawString("GAME OVER", 600, 20);

	}

	public void addActores(List<Personaje> actores) {
		this.actores=actores;
		
	}
	
	
}