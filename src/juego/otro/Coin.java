package juego.otro;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin extends Escenario {
	 
	private String imgName;
	private int x;
	private int y;
	protected boolean markedForRemoval;
    public Coin(String imgName,int i, int j) {
        super(i,j);
        this.imgName=imgName;
        loadInformations();
    }
 
    @Override
    protected void initializeStuff() {
        x=col*Escenario_SIZE;
        y=row*Escenario_SIZE;
        boundingBox=new Rectangle(x,y,Escenario_SIZE,Escenario_SIZE);
    }
     

	public void remove() {
		 markedForRemoval = true;
		}
		    
	public boolean isMarkedForRemoval() {
	      return markedForRemoval;
	}

	@Override
	protected void loadInformations() {
		// TODO Auto-generated method stub
		
	}
     
  
}