package juego.otro;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Personaje {

	protected int currentX,currentY;
    protected int width, height;
	protected BufferedImage paradoR;
	protected BufferedImage paradoL;
	protected BufferedImage[] run_R;
	protected BufferedImage[] run_L;
    ArrayList <String> listaSprites;
    protected BufferedImage currentFrame;
    protected int currentFrameNumber=0;
    //para que los sprites cambien a la velocidad que queramos
    protected int frameSpeed;
    protected int t;
    protected int moveCounter=0;
    protected static final int MOVE_COUNTER_THRESH=5;
    protected boolean markedForRemoval;
    public Rectangle boundingBox;

	public Personaje() {
		super();
	}

	public int getFrameSpeed() {return frameSpeed;  }
	public void setFrameSpeed(int i) {frameSpeed = i; }
	protected void setFrameNumber() {}
	public void collision(Object objeto) {}
	public void move() {}
	public void loadSprites() {}
	public BufferedImage getCurrentFrame(){
		return currentFrame;
	}
	public void remove() {
		 markedForRemoval = true;
		}
		    
	public boolean isMarkedForRemoval() {
	      return markedForRemoval;
	}
	public void paint(Graphics2D g2) {
		boundingBox.setLocation(currentX, currentY);
		g2.drawImage(this.getCurrentFrame(),this.getCurrentX(),this.getCurrentY(),null);
		
	}
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	public int getCurrentX() {
		return currentX;
	}
	
	public int getCurrentY() {
		return currentY;
	}
}