package juego.ui;


import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//the main game frame of the game 
public class GameFrame extends JFrame {
	public static final int WIDTH=1280;
	public static final int HEIGHT=640;


	public GameFrame(GamePanel gamePanel){
		
		
		//Para que aparezca en la mitad de la pantalla
		this.setLocation((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-WIDTH)/2),
				((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-HEIGHT)/2));		
	
		//para establecer el tamaño
		this.setSize(WIDTH,HEIGHT);
		
		//para establecer el título
		this.setTitle("Proyecto Plataformas");
		
		//para hacerlo visible
		this.setVisible(true);
		
		//operacion por defecto al dar a cerrar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//para que no se pueda reajustar 
		this.setResizable(false);
		
		this.add(gamePanel);
		
        //Aqui establecemos el comportamiento a la hora de cerrar. Para ello utilizaremos el metodo
        //addWindowListener que tiene todo JFrame como es nuestra ventana y le pasaremos como parametro
        //un WindowAdapter, que al ser un adaptador de la interfaz WindowListener habrá de ser sobreescrito 
        //el método que utilicemos ya que los implementa como void, en
        //este caso windowClosing
		addWindowListener( new WindowAdapter() {
	          public void windowClosing(WindowEvent e) {
	        	  //Medio para preguntar si queremos cerrar el programa de verdad o no
	        	  int respuesta= JOptionPane.showConfirmDialog(null, "¿Realmente desea salir del juego?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	        	  if (respuesta==0) System.exit(0);
	         }
	        });
		
		gamePanel.grabFocus();
		gamePanel.requestFocusInWindow();
	}
	

}