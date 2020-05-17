package juego;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import entidades.*;

public class ManejoArchivos {

	private static final String RUTA = "recursos/";
	private static final String AVENTURA_JSON = RUTA + "aventura.json";
	
	//agregar librería gson a eclipse
	//link: https://medium.com/programmers-blockchain/importing-gson-into-eclipse-ec8cf678ad52
		
	//"interpretar" archivo json para instanciar nuestros objetos
	//link: https://stackoverflow.com/questions/29965764/how-to-parse-json-file-with-gson
		
	//visulizador json online //pegás el texto y se visualiza mejor para el ser humano(?
	//link: http://jsonviewer.stack.hu/
		
	//editor json que encontré por ahí
	//link: https://jsoneditoronline.org/
	
	public static Aventura getAventura() {
		Aventura aventura = null;
		try {
			JsonReader reader = new JsonReader(new FileReader(AVENTURA_JSON));
			aventura = new Gson().fromJson(reader,Aventura.class);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return aventura;
	}
	
}
