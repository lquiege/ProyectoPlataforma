package juego.otro;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import juego.ui.GameFrame;
import juego.ui.PlayPanel;

public class Enemigos extends Personaje {

	//Tamaño del Tile
	private static final int TILE_SIZE = 32;
	//Empieza mirando a la derecha
//	private int last_direction=KeyEvent.VK_RIGHT;
			
		
	private static final int FRAMESPEED=3;
		


	//Hitbox del personaje
	public Rectangle boundingBox;
		
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}



	//Desplazamiento
	private int DISPLACEMENT=4;
	private int DISPLACEMENTY=4;
	//Potencia Salto
	private static final int PSALTO=-20;
		
	//Frame actual
	private BufferedImage currentFrame;
		
	@Override
	public String toString() {
		return "Enemigos [boundingBox=" + boundingBox + ", currentFrame=" + currentFrame + ", currentX=" + currentX
				+ ", currentY=" + currentY + ", paradoR=" + paradoR + ", paradoL=" + paradoL + ", run_R="
				+ Arrays.toString(run_R) + ", run_L=" + Arrays.toString(run_L) + "]";
	}

	//Imagen para el salto
	private BufferedImage salto;
		
	//Numero del frame/sprite
	private int currentFrameNumber=0;
		
	//altura del personaje
	private final static int ENEMIGO_HEIGHT=TILE_SIZE;
				
	//ancho del personaje
	private final static int ENEMIGO_WIDTH=TILE_SIZE;
		
	
	public Enemigos(int currentX, int currentY, int direccion){
		//Los buffers con las imagenes para correr a derecha e izquierda
		run_L=new BufferedImage[2];
		run_R=new BufferedImage[2];
		this.currentY=currentY;
		if(this.currentY>=Protagonista.MARIO_START_Y) this.currentY=Protagonista.MARIO_START_Y*(int)Math.random();

		this.currentX = currentX;

		
		//carga los sprites
		loadInformations();
		
		//al principio el personaje sale mirando a la derecha
		currentFrame=paradoL;
		
		//la hitbox del personaje
		boundingBox=new Rectangle(currentX,currentY,ENEMIGO_WIDTH,ENEMIGO_HEIGHT);
		
		
	}

	
	public void paint(Graphics2D g) {
		boundingBox.setLocation(currentX, currentY);
		g.drawImage(this.getCurrentFrame(),this.getCurrentX(),this.getCurrentY(),null);
	}

	public void setFrameNumber() {
		currentFrameNumber  = moveCounter/FRAMESPEED;
		currentFrameNumber %= 2;
	
		if(moveCounter>FRAMESPEED*2){
			moveCounter=0;
		}
	}
	
	private void loadInformations() {
		try {
			paradoR=ImageIO.read(getClass().getResource("/juego/res/koopa1.png"));
			paradoL=ImageIO.read(getClass().getResource("/juego/res/koopader1.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getCurrentFrame(){
		return currentFrame;
	}
	
	public void move() {
		       currentX+=DISPLACEMENT;
		       if (currentX < 0 || currentX>GameFrame.WIDTH-ENEMIGO_WIDTH) {
		    	   DISPLACEMENT = -DISPLACEMENT;
		    	   if(currentX<0) currentFrame=paradoL;
		    	   if(currentX>GameFrame.WIDTH-ENEMIGO_WIDTH) currentFrame=paradoR;
		       }   
		       currentY+=DISPLACEMENTY;
		       if (currentY < 0 || currentY>=/*GameFrame.HEIGHT*/Protagonista.MARIO_START_Y)
		    	   DISPLACEMENTY = -DISPLACEMENTY;
		     }

	public void setCurrentX(int currentX) {
		this.currentX= currentX;
	}
	
	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
	
	public void collision(Object objeto) {
		if(objeto instanceof Bola)	remove();
		if(objeto instanceof Protagonista) {
		remove();
		}
	
	}

}
