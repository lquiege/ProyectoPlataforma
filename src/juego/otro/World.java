package juego.otro;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class World {
	public static BufferedImage CURRENT_BACKGROUND;
	public static Escenario[][] EscenariodMap;
	public static final int ROWS=9;
	public static final int COLS=20;
	public List <Escenario> elementos = new ArrayList <Escenario>();
	
	public World(){
		EscenariodMap=new Escenario[ROWS][COLS];
	}
	
	public void initializeStage(int level){
		try {
			CURRENT_BACKGROUND=ImageIO.read(getClass().getResource("/juego/res/background"+level+".png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		InputStream is=this.getClass().getResourceAsStream("level"+String.valueOf(level)+".txt");
		BufferedReader reader=new BufferedReader(new InputStreamReader(is));
		String line=null;
		String[] EscenariosInLine=new String[ROWS];
		try {
			int i=0;
			while((line=reader.readLine())!=null){
				EscenariosInLine=line.split(" ");
				for(int j=0; j<COLS; j++){
					if(!EscenariosInLine[j].equalsIgnoreCase("empt")){
						EscenariodMap[i][j]=newEscenarioInstance(EscenariosInLine[j],i,j);					
					} else {
						EscenariodMap[i][j]=null;
					}
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private Escenario newEscenarioInstance(String name, int i, int j) {
		switch (name) {
			case "ter0":
				elementos.add(new Block("ter0", i, j));
				return new Block("ter0", i, j);
			case "ter1":
				elementos.add(new Block("ter1", i, j));
				return new Block("ter1", i, j);
			case "terR":
				elementos.add(new Block("terR", i, j));
				return new Block("terR", i, j);
			case "terL":
				elementos.add(new Block("terL", i, j));
				return new Block("terL", i, j);
			case "terQ":
				elementos.add(new Block("terQ", i, j));
				return new Block("terQ", i, j);
			case "terP":
				elementos.add(new Block("terP", i, j));
				return new Block("terP", i, j);
			case "term":
				elementos.add(new Block("term", i, j));
				return new Block("term", i, j);
			case "coin":
				elementos.add(new Coin("coin", i, j));
				return new Coin("coin", i, j);
		}
		return null;
	}

	public List<Escenario> getElementos() {
		return elementos;
	}

	public void setElementos(List<Escenario> elementos) {
		this.elementos = elementos;
	}
	

}
