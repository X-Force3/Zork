package juego;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import entidades.*;

/**
 * Ver: https://howtodoinjava.com/gson/custom-serialization-deserialization/
 * lista<T>
 * https://stackoverflow.com/questions/14673694/deserialize-json-string-with-gson/14673950#14673950
 * list<T> con custom deserializater
 * https://stackoverflow.com/questions/15522803/custom-serializer-deserializer-using-gson-for-a-list-of-basicnamevaluepairs
 */

public class ManejoArchivos {

	private Map<String, Ubicacion> ubicacionesMap = new HashMap<String, Ubicacion>();
	private Map<String, Item> itemsMap = new HashMap<String, Item>();
	
	//variables que necesitamos para construir la aventura
	private Configuracion configuracion;
	private List<Ubicacion> ubicaciones;
	
	//para no instanciar gson multiples veces
	private Gson simpleGson = new Gson(); 

	public ManejoArchivos(String aventuraPath) {
		JsonObject o = convertFileToJSON(aventuraPath);
		construir(o);
	}

	private void contruirItemsDeLugares(JsonArray arrayUbicaciones, List<Ubicacion> ubicaciones) {
		for(int i = 0; i < ubicaciones.size(); i++) {
			JsonArray arrayLugares = arrayUbicaciones.get(i).getAsJsonObject().getAsJsonArray("lugares");
			for(int j = 0; j < arrayLugares.size(); j++) {
				List<Item> items = new ArrayList<Item>();
				JsonArray arrayItems = arrayLugares.get(j).getAsJsonObject().getAsJsonArray("itemsNombre");
				for(int k = 0; k < arrayItems.size(); k++)
					items.add(itemsMap.get(arrayItems.get(k).getAsString()));
				ubicaciones.get(i).getLugares().get(j).setItems(items);
			}
			ubicacionesMap.put(ubicaciones.get(i).getNombre(), ubicaciones.get(i));
		}
	}

	protected void construir(JsonObject jo) {
		this.configuracion = simpleGson.fromJson(jo.get("configuracion"), Configuracion.class);

		// items
		Type listTypeItem = new TypeToken<ArrayList<Item>>() {}.getType();
		ArrayList<Item> items = simpleGson.fromJson(jo.get("items"), listTypeItem);
		for (Item item : items) {
			itemsMap.put(item.getNombre(), item);
		}
		
		// ubicaciones
		Type listTypeUbicacion = new TypeToken<ArrayList<Ubicacion>>() {}.getType();
		ArrayList<Ubicacion> ubicaciones = simpleGson.fromJson(jo.get("ubicaciones"), listTypeUbicacion);
		contruirItemsDeLugares(jo.getAsJsonArray("ubicaciones"),ubicaciones);

		this.ubicaciones = ubicaciones;
	}
	
	public static JsonObject convertFileToJSON(String path) {
		JsonObject jsonObject = new JsonObject();
		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(new FileReader(path));
			jsonObject = jsonElement.getAsJsonObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return jsonObject;
	}

	public Map<String, Ubicacion> getUbicacionesMap() {
		return ubicacionesMap;
	}

	public Map<String, Item> getItemsMap() {
		return itemsMap;
	}
	
	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public static void main(String[] args) {
		ManejoArchivos m = new ManejoArchivos("recursos/aventura.json");
		
		System.out.println("\n\nUbicaciones: ");
		for (Map.Entry<String, Ubicacion> entry : m.getUbicacionesMap().entrySet()) {
		    System.out.println("clave=" + entry.getKey() + ", \nvalor=" + entry.getValue());
		}
		
		System.out.println("\n\nItems: ");
		for (Map.Entry<String, Item> entry : m.getItemsMap().entrySet()) {
		    System.out.println("clave=" + entry.getKey() + ", \nvalor=" + entry.getValue());
		}
	}
}
