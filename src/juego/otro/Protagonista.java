package juego.otro;




import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import juego.ui.*;

public class Protagonista extends Personaje {

	public static int puntuacion=0;
		
	//Tamaño del Tile
	private static final int TILE_SIZE = 32;
	//Empieza mirando a la derecha
	private int last_direction=KeyEvent.VK_RIGHT;
			
		
	private static final int FRAMESPEED=3;
		
		
	private int moveCounter=0;
	private int gravity=0;
	private int inercia=0;
	private int cantidadFuego=0;
	private boolean caida=false;
		
	public int getCantidadFuego() {
		return cantidadFuego;
	}
	
	public static int cantidadVida=3;
	
	public void setCantidadFuego(int cantidadFuego) {
		this.cantidadFuego = cantidadFuego;
	}

	protected boolean parado;
		
	public static int actualY;
	private boolean rebote;

	//Hitbox del personaje
	public Rectangle boundingBox;
		
	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	//Desplazamiento
	private static final int DISPLACEMENT=4;
	//Potencia Salto
	private static final int PSALTO=-20;
		
	//Frame actual
	private BufferedImage currentFrame;
		
	//Imagen para el salto
//	private BufferedImage salto;
		
	//Numero del frame/sprite
	private int currentFrameNumber=0;
		
	//altura del personaje
	private final static int MARIO_HEIGHT=TILE_SIZE;
				
	//ancho del personaje
	private final static int MARIO_WIDTH=TILE_SIZE;
		
	//donde comienza el personaje
	public static final int MARIO_START_X=128;
	public static final int MARIO_START_Y=GameFrame.HEIGHT-PlayPanel.TERRAIN_HEIGHT-MARIO_HEIGHT;
		
		
		
	//posicion inicial del personaje
//	private int currentX=MARIO_START_X;
		
	//altura inicial del personaje
//	private int currentY=MARIO_START_Y;
	
	private boolean falling=true;

	private Boolean lastArriba = false;
		
	public Protagonista(){
		super();
		//Los buffers con las imagenes para correr a derecha e izquierda
		run_L=new BufferedImage[2];
		run_R=new BufferedImage[2];
		
		currentX=MARIO_START_X;
		currentY=MARIO_START_Y-10;
		actualY=currentY;
		//carga los sprites
		loadInformations();
		
		//al principio el personaje sale mirando a la derecha
		currentFrame=paradoR;
		
		//la hitbox del personaje
		boundingBox=new Rectangle(MARIO_START_X+DISPLACEMENT,currentY,MARIO_WIDTH,MARIO_HEIGHT);
	}

	public void paint(Graphics2D g) {
		boundingBox.setLocation(currentX, currentY);
		if(rebote) {
			g.drawImage(this.getCurrentFrame(),this.getCurrentX(),this.getCurrentY()+1,null);
		}
		g.drawImage(this.getCurrentFrame(),this.getCurrentX(),this.getCurrentY(),null);
	}
	 
	 
	//método para cargar las imagenes
	private void loadInformations() {
		try {
			paradoR=ImageIO.read(getClass().getResource("/juego/res/mario0.png"));
			paradoL=ImageIO.read(getClass().getResource("/juego/res/marioiz0.png"));
			
			run_R[0]=ImageIO.read(getClass().getResource("/juego/res/mario1.png"));
			run_L[0]=ImageIO.read(getClass().getResource("/juego/res/marioiz1.png"));
			
			run_R[1]=ImageIO.read(getClass().getResource("/juego/res/mario2.png"));
			run_L[1]=ImageIO.read(getClass().getResource("/juego/res/marioiz2.png"));
			
		
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getLast_direction() {
		return last_direction;
	}

	public void setLast_direction(int last_direction) {
		this.last_direction = last_direction;
	}

	//funcion llamada por GameManager para gestionar las teclas
	public void move(int direction) {
		switch (direction) {
			case KeyEvent.VK_LEFT:
				
				currentX=currentX-DISPLACEMENT;
				if(currentX<PlayPanel.WIDTH) currentX=0;
	
				//cambia el frame actual
				if(!getJumping()){
					setFrameNumber();
					currentFrame=run_L[currentFrameNumber];
				} else {
				
				}

				currentFrame=run_L[currentFrameNumber];
				last_direction=KeyEvent.VK_LEFT;
				break;
			
			case KeyEvent.VK_RIGHT:
				
				currentX=currentX+DISPLACEMENT;
				if(currentX>GameFrame.WIDTH-MARIO_WIDTH-8) currentX=1280-MARIO_WIDTH-8;
	
				if(!getJumping()){
					setFrameNumber();
					currentFrame=run_R[currentFrameNumber];
				} else {
		
				}
				currentFrame=run_R[currentFrameNumber];
				last_direction=KeyEvent.VK_RIGHT;
				break;
				
			default:
				break;
		}
		moveCounter++;
	}
		
	//Comprobar si está cayendo o no
	public void checkState() {
		currentY+=gravity;
		actualY=currentY;
		
		if (currentY<PlayPanel.PLAY_PANEL_HEIGHT && !lastArriba && caida || falling && currentY<PlayPanel.PLAY_PANEL_HEIGHT && caida) {
			gravity++;
			if (gravity==0) {
				gravity++;
			}
		} else {
			gravity=0;
			this.lastArriba = false;
		}
	}
	
	//Comienza a saltar
	public void jump() {
		falling=true;
		gravity=PSALTO;
		caida=true;
	}
	
	public void stop() {
		if(last_direction==KeyEvent.VK_RIGHT){
			currentFrame=paradoR;
		} else {
			currentFrame=paradoL;
		}
	}

	public void setFrameNumber() {
		currentFrameNumber  = moveCounter/FRAMESPEED;
		currentFrameNumber %= 2;
	
		if(moveCounter>FRAMESPEED*2){
			moveCounter=0;
		}
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public BufferedImage getCurrentFrame(){
		return currentFrame;
	}

	public int getCurrentX(){
		return currentX;
	}
	
	public int getCurrentY(){
		return currentY;
	}
	
	//gets de la hitbox
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	public boolean getJumping() {
		return !(gravity==0);

	}
	
	public static int getActualY() {
		return actualY;
	}
	

	public void collision(Escenario elementoActual) {

		if (elementoActual instanceof Block) {
			Rectangle elementoRec = elementoActual.getBoundingBox();
			Rectangle protaRec = this.getBoundingBox();
			
			
			Rectangle recarriba = ((new Rectangle()));
			recarriba.setBounds(elementoRec.x, elementoRec.y, elementoRec.width, elementoRec.height/2);
			
			Rectangle recabajo = ((new Rectangle()));
			recabajo.setBounds(elementoRec.x, elementoRec.y+elementoRec.height/2, elementoRec.width, elementoRec.height/2);
			
			Rectangle recizq = ((new Rectangle()));
			recizq.setBounds(elementoRec.x, elementoRec.y, elementoRec.width/2, elementoRec.height);
			
			Rectangle recder = ((new Rectangle()));
			recder.setBounds(elementoRec.x+elementoRec.width/2, elementoRec.y, elementoRec.width/2, elementoRec.height);
			
			Boolean arriba = protaRec.intersects(recarriba);
			Boolean abajo = protaRec.intersects(recabajo);
			Boolean izquierda = protaRec.intersects(recizq);
			Boolean derecha = protaRec.intersects(recder);
			
			int arribaB = (int)elementoActual.getBoundingBox().getMinY();

			setGravity(0);
			if (arriba && !lastArriba) {
				this.currentY = arribaB-MARIO_HEIGHT;
				falling = false;
				rebote=true;
				caida=false;
			}
			else rebote=false;
			
			if (abajo) {
				checkState();
			} 

			this.lastArriba = arriba;
		}

		if(elementoActual instanceof Coin) {
			
			Rectangle elementoRec = elementoActual.getBoundingBox();
			Rectangle protaRec = this.getBoundingBox();
			
			Rectangle recarriba = ((new Rectangle()));
			recarriba.setBounds(elementoRec.x+MARIO_WIDTH/2, elementoRec.y, elementoRec.width-MARIO_WIDTH, elementoRec.height);
			Boolean arriba = protaRec.intersects(recarriba);
			
			if (arriba) {
				caida=true;
			}	
		}
	}
	
	public static int getCantidadVida() {
		return cantidadVida;
	}

	public static void setCantidadVida(int cantidadVida) {
		Protagonista.cantidadVida = cantidadVida;
	}

	public void collision(Object enemigos) {
		if (enemigos instanceof Enemigos)
		cantidadVida--;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
}
