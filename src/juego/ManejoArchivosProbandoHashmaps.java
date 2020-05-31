package juego;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import entidades.*;

public class ManejoArchivosProbandoHashmaps {

	private static final String RUTA = "recursos/";
	public static final String RUTA_AVENTURA_1 = RUTA + "aventura.json";
	private Aventura aventura;

	public ManejoArchivosProbandoHashmaps(String ruta) {
		this.aventura = cargarAventura(ruta);
	}

	private static Aventura cargarAventura(String ruta) {
		Aventura aventura = null;
		try {
			JsonReader reader = new JsonReader(new FileReader(ruta));
			aventura = new Gson().fromJson(reader, Aventura.class);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aventura;
	}

	public HashMap<String, Ubicacion> construirHashmapUbicaciones() {
		HashMap<String, Ubicacion> ubicacionesHashMap = new HashMap<String, Ubicacion>();
		for (Ubicacion ubicacion : aventura.getUbicaciones()) {
			ubicacionesHashMap.put(ubicacion.getNombre(), ubicacion);
		}
		return ubicacionesHashMap;
	}

	public HashMap<String, Item> construirHashmapItems() {
		HashMap<String, Item> itemsHashMap = new HashMap<String, Item>();
		for (Item item : aventura.getItems()) {
			itemsHashMap.put(item.getNombre(), item);
		}
		return itemsHashMap;
	}

	public Aventura getAventura() {
		return aventura;
	}

}
