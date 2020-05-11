package pruebas;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import juego.Juego;

public class PruebasJuego {
	
	/**
	 * 
		//agregar librería gson a eclipse
		//link: https://medium.com/programmers-blockchain/importing-gson-into-eclipse-ec8cf678ad52
		
		//"interpretar" archivo json para instanciar nuestros objetos
		//link: https://stackoverflow.com/questions/29965764/how-to-parse-json-file-with-gson
		
		//visulizador json online //pegás el texto y se visualiza mejor para el ser humano(?
		//link: http://jsonviewer.stack.hu/
		
		//editor json que encontré por ahí
		//link: https://jsoneditoronline.org/
	 * */

	@Test
	public void leerJson() {
		
		Gson gson = new Gson();
		Juego juego = null;
		try {
			JsonReader reader = new JsonReader(new FileReader("recursos/juego.json"));
			juego = gson.fromJson(reader,Juego.class);
			System.out.println(juego);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			Assert.assertEquals(true, juego != null);
		}
	}
}
