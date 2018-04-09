package juego.otro;
//package intermediary;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

import juego.ui.*;

// Esta clase llama al repaint del PlayPanel y gestiona las teclas que se presionan

public class GameManager  {
	// True si el juego est� funcionando, falso si no
	private boolean gameIsRunning;

	// Necesita estar el GamePanel y el GameFrame (lo que se dibuja en la ventana y
	// la ventana)
	private GamePanel gamePanel;
	private GameFrame gameFrame;
	private static final int MAIN_SLEEP_TIME = 18;
	private Protagonista protagonista;
	private World world;
	private int currentLevel = 1;
	private List<Escenario> elementos = new ArrayList<Escenario>();
	private List<Personaje> actores =new ArrayList<Personaje>();
	private int resp;

	public GameManager(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		this.world = new World();
		this.world.initializeStage(currentLevel);
		gameIsRunning = true;
		this.gamePanel = gamePanel;

		// run();
	}

	
	public void run() {
		
		
		
		do {
			initWorld();
			protagonista = new Protagonista();
			protagonista.setCantidadVida(3);
			this.gamePanel.addProtagonista(protagonista);
			this.gamePanel.addActores(actores);
			this.elementos = this.world.getElementos();
			while (gameIsRunning && protagonista.cantidadVida>0 && protagonista.getCurrentY()<=GameFrame.HEIGHT) {
				manageKeys();
				updateWorld();
				checkCollisions();
				gamePanel.repaintGame();
				
				
		
				try {
					Thread.sleep(MAIN_SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				resp = JOptionPane.showConfirmDialog(null, "�Quieres volver a jugar?", null, JOptionPane.YES_NO_OPTION);
		}while(resp==0);
		if(resp==1) System.exit(0);	
	}

	// Esta funcion controla las teclas que se presionan asoci�ndolas a acciones
	private void manageKeys() {
		// Con esto recuperamos las teclas presionadas desde la clase KeyboardControl
		HashSet<Integer> currentKeys = KeyboardControl.getActiveKeys();
		protagonista.checkState();
		// Para gestionar las direcciones del personaje
		if (currentKeys.contains(KeyEvent.VK_RIGHT)) {
			protagonista.move(KeyEvent.VK_RIGHT);
			protagonista.puntuacion += 20;
		}
		if (currentKeys.contains(KeyEvent.VK_UP)) {
			protagonista.move(KeyEvent.VK_UP);
		} else if (currentKeys.contains(KeyEvent.VK_LEFT)) {
			protagonista.move(KeyEvent.VK_LEFT);
			protagonista.puntuacion -= 20;
		}
		if (currentKeys.contains(KeyEvent.VK_SPACE)) {
			if (!protagonista.getJumping()) {
				protagonista.jump();
			}
		}		
		if (currentKeys.contains(KeyEvent.VK_A)) {
			if(protagonista.getCantidadFuego()<1) {
				actores.add(new Bola(protagonista.getLast_direction(),protagonista.getCurrentX(),protagonista.getCurrentY()));
			}
			protagonista.setCantidadFuego(1);
		}
		if (currentKeys.isEmpty())
			protagonista.stop();
	}
	
	 public void initWorld() {
		    actores = new ArrayList<Personaje>();
		    for (int i = 0; i < 10; i++){
		        int x = (int)(Math.random()*GameFrame.WIDTH);
		        int y = i*Escenario.Escenario_SIZE;
		        Enemigos m = new Enemigos(x, y, i%2);
		        actores.add(m); 
		    }
	}
	 
	 public void updateWorld() {
		    for (int i = 0; i < actores.size(); i++) {
		        Personaje m = actores.get(i);
		        if (m instanceof Bola) {
		        	if(m.isMarkedForRemoval()) protagonista.setCantidadFuego(protagonista.getCantidadFuego()-1);
		        }
		        if (m.isMarkedForRemoval()) actores.remove(m);       
		        else m.move();	
		       }
	 }
	 
	 
	 public void checkCollisions() {
		  for (int i = 0; i < actores.size(); i++) {
			 Personaje m = actores.get(i);
			 if (m.getBoundingBox().intersects(protagonista.boundingBox)) {
				 m.collision(protagonista);
				 protagonista.collision(m);				 
			 }
			 
			 for(int j=0; j<this.actores.size();j++) {
				Personaje m2 = actores.get(j);
		        if (m.getBoundingBox().intersects(m2.getBoundingBox()) && m != m2) {
		        	m.collision(m2);
		        	m2.collision(m);
		        	}
		        }
		     
		
			 for(int j=0; j<this.elementos.size();j++) {
		        if (m.getBoundingBox().intersects(this.elementos.get(j).boundingBox)) m.collision(this.elementos.get(j));
		        }
			  }
			for (int i = 0; i < this.elementos.size(); i++) {
				Escenario elementoActual = this.elementos.get(i);

				if(this.protagonista.getBoundingBox().intersects(elementoActual.getBoundingBox())) {
					protagonista.collision(elementoActual);
				}
			}
	 }
}