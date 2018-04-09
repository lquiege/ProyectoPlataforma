package juego.otro;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends Escenario {

	private String imgName;
	private int x;
	private int y;

	public Block(String imgName, int i, int j) {
		super(i, j);
		this.imgName = imgName;
		loadInformations();
	}

	protected void initializeStuff() {
		x = col * Escenario_SIZE;
		y = row * Escenario_SIZE;
		boundingBox = new Rectangle(x, y, Escenario_SIZE, Escenario_SIZE);
	}

	protected void loadInformations() {
		try {
			image = ImageIO.read(getClass().getResource("/juego/res/" + imgName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Block [imgName=" + imgName + ", x=" + x + ", y=" + y + "]";
	}

}