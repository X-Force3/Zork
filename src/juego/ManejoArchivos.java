package juego;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import entidades.*;

public class ManejoArchivos {

	private static final String RUTA = "recursos/";
	private static final String AVENTURAS_JSON = RUTA + "aventuras.json";
	private static final String LOCACIONES_JSON = RUTA + "locaciones.json";
	private static final String NPCS_JSON = RUTA + "npcs.json";
	private static final String ITEMS_JSON = RUTA + "items.json";

	private static <T> List<T> getObjetos(String ruta) {
		List<T> objetos = null;
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader(ruta));
			objetos = new Gson().fromJson(reader,new TypeToken<List<T>>(){}.getType());
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objetos;
	}

	public static List<Aventura> getAventuras() {
		return getObjetos(AVENTURAS_JSON);
	}

	public static List<Locacion> getLocaciones() {
		return getObjetos(LOCACIONES_JSON);
	}

	public static List<Npc> getNpcs() {
		return getObjetos(NPCS_JSON);
	}

	public static List<Item> getItems() {
		return getObjetos(ITEMS_JSON);
	}
	
	
	//agregar librería gson a eclipse
	//link: https://medium.com/programmers-blockchain/importing-gson-into-eclipse-ec8cf678ad52
		
	//"interpretar" archivo json para instanciar nuestros objetos
	//link: https://stackoverflow.com/questions/29965764/how-to-parse-json-file-with-gson
		
	//visulizador json online //pegás el texto y se visualiza mejor para el ser humano(?
	//link: http://jsonviewer.stack.hu/
		
	//editor json que encontré por ahí
	//link: https://jsoneditoronline.org/
	/* *	
	Juego juego = null;
	@Before
	public void leerJson() {
		Gson gson = new Gson();
		try {
			JsonReader reader = new JsonReader(new FileReader("recursos/juego.json"));
			juego = gson.fromJson(reader,Juego.class);
			System.out.println(juego);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			Assert.assertTrue(juego != null);
		}
	}
	 * */
}
