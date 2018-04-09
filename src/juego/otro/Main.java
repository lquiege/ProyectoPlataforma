package juego.otro;
import juego.ui.*;

public class Main {
	public static void main(String[] args) {
		//inicializar el panel
		GamePanel gamePanel=new GamePanel();
		
		//inicializar la clase que gestiona el juego
		GameManager gameManager=new GameManager(gamePanel);
		
		GameFrame gameFrame=new GameFrame(gamePanel);
		gameManager.run();
		

	}
}