package juego.otro;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Escenario {
	
	protected int row;
	protected int col;
	protected BufferedImage image;
	protected Rectangle boundingBox;
	public static final int Escenario_SIZE=64;
	public Escenario(int i, int j){
		this.row=i;
		this.col=j;
		initializeStuff();
	}
	
	protected abstract void initializeStuff();
	
	protected abstract void loadInformations();
	
	public BufferedImage getImage(){
		return image;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	
}