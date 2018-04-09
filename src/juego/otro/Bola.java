package juego.otro;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import juego.ui.GameFrame;

public class Bola extends Personaje {
	  protected int BULLET_SPEED=10;
	  int direccion;
	  Rectangle boundingBox;
	  private BufferedImage currentFrame;
	  private static final int TILE_SIZE = 32;
	  private int currentFrameNumber=0;
	  
	  public Bola(int direccion, int X, int Y) {
	  this.direccion=direccion;
	  this.currentX=X;
	  this.currentY=Y;
		//Frame actual
		
	  
	  boundingBox = new Rectangle(currentX,currentY,TILE_SIZE,TILE_SIZE);
	  try {
		  paradoL=ImageIO.read(getClass().getResource("/juego/res/bola.png"));
		 } catch (IOException e) {
				// TODO Auto-generated catch block
			 e.printStackTrace();
		 	}
	  
		currentFrame=paradoL;
	  }
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	public BufferedImage getCurrentFrame(){
		return currentFrame;
		}
	
	  
	public void paint(Graphics2D g) {
		this.boundingBox.setLocation(currentX, currentY);
		g.drawImage(this.getCurrentFrame(),this.getCurrentX(),this.getCurrentY(),null);
		}
	public void remove() {
		markedForRemoval = true;
	}
	        
	public void move() {

	   if (this.direccion==KeyEvent.VK_RIGHT) {
	   	 currentX+=BULLET_SPEED;
         }
       if (this.direccion==KeyEvent.VK_LEFT) {
       	 currentX-=BULLET_SPEED;
         }	          
       if (currentX < 0 || currentX>juego.ui.GameFrame.WIDTH)
       	 remove();
         }
	        
	public void collision(Object actor) {
		
       if(actor instanceof Enemigos)    
    	   remove();
	}
	  
	  public int getCurrentY() {
			// TODO Auto-generated method stub
			return currentY;
	  }

	  public int getCurrentX() {
			// TODO Auto-generated method stub
			return currentX;
	  }
}
