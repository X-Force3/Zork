package juego;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import entidades.*;

/**
 * Ver: https://howtodoinjava.com/gson/custom-serialization-deserialization/
 * lista<T>
 * https://stackoverflow.com/questions/14673694/deserialize-json-string-with-gson/14673950#14673950
 * list<T> con custom deserializater
 * https://stackoverflow.com/questions/15522803/custom-serializer-deserializer-using-gson-for-a-list-of-basicnamevaluepairs
 */

public class ManejoArchivos {

	private static final String RUTA = "recursos/";
	private static final String AVENTURA_JSON = RUTA + "aventura.json";

	private Map<String, Ubicacion> ubicacionesMap = new HashMap<String, Ubicacion>();
	private Map<String, Item> itemsMap = new HashMap<String, Item>();
	private Aventura aventura;

	public ManejoArchivos() {
		JsonObject o = convertFileToJSON(AVENTURA_JSON);
		aventura = construir(o);
	}

	protected Aventura construir(JsonObject jo) {
		Gson gson = new Gson();
		Configuracion config = gson.fromJson(jo.get("configuracion"), Configuracion.class);

		// items
		Type listTypeItem = new TypeToken<ArrayList<Item>>() {}.getType();
		ArrayList<Item> items = new Gson().fromJson(jo.get("items"), listTypeItem);
		for (Item item : items) {
			itemsMap.put(item.getNombre(), item);
		}
		
		//lugares
		/*Type typeLugares = new TypeToken<ArrayList<Lugar>>() {}.getType();
		JsonDeserializer<Lugar> desLugar = new JsonDeserializer<Lugar>() {

			@Override
			public Lugar deserialize(JsonElement jsonElement, Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				return deserializarLugar(jsonElement.getAsJsonObject());
			}

		};
		GsonBuilder gsonBuilder2 = new GsonBuilder();
		gsonBuilder2.registerTypeAdapter(Lugar.class, desLugar);
		Gson gson2 = gsonBuilder2.create();
		ArrayList<Ubicacion> lugaresss = gson2.fromJson(jo.get("ubicaciones"), typeLugares);*/

		// ubicaciones
		Type listTypeUbicaciones = new TypeToken<ArrayList<Ubicacion>>() {}.getType();
		JsonDeserializer<Ubicacion> desUbicacion = new JsonDeserializer<Ubicacion>() {

			@Override
			public Ubicacion deserialize(JsonElement jsonElement, Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				return deserializarUbicacion(jsonElement.getAsJsonObject());
			}

		};
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Ubicacion.class, desUbicacion);
		Gson gson1 = gsonBuilder.create();
		ArrayList<Ubicacion> ubicacionesss = gson1.fromJson(jo.get("ubicaciones"), listTypeUbicaciones);

		
		return new Aventura(config, ubicacionesss);
	}
	
	protected Lugar deserializarLugar(JsonObject jo) {
		List<Item> items = new ArrayList<>();
		System.out.println(jo.toString());
		JsonArray array = jo.getAsJsonArray("items");
		for(int i = 0; i < array.size(); i++) {
			items.add(itemsMap.get(array.get(i).getAsString()));
		}
		Gson g = new Gson();
		Type typeTriggerList = new TypeToken<ArrayList<Trigger>>() {}.getType();
		return new Lugar(
				/*nombre*/jo.get("nombre").getAsString(),
				/*segundoN*/jo.get("segundoNombre").getAsString(),
				/*genero*/g.fromJson(jo.get("genero"), Genero.class),
				/*numero*/g.fromJson(jo.get("numero"), Numero.class),
				/*items*/items,
				/*triggers*/new Gson().fromJson(jo.get("triggers"), typeTriggerList),
				/*descrip*/jo.get("descripcion").getAsString()
				);
	}

	protected Ubicacion deserializarUbicacion(JsonObject jo) {
		List<Lugar> lugares = deserializeLugaresObjeto(jo.getAsJsonArray("lugares"));
		List<Npc> npcs = deserealizarNpcs(jo.get("npcs").getAsJsonArray());
		List<Conexion> conexiones = deserealizarConexiones(jo.get("conexiones").getAsJsonArray());
		//String nombre, Genero genero, Numero numero, String descripcion, List<Lugar> lugares,
		//List<Npc> npcs, List<Conexion> conexiones
		Ubicacion ubi = new Ubicacion(
				/*nombre*/jo.get("nombre").getAsString(),
				/*genero*/new Gson().fromJson(jo.get("genero"), Genero.class),
				/*numero*/new Gson().fromJson(jo.get("numero"), Numero.class), 
				/*descripcion*/jo.get("descripcion").getAsString(),
				/*lugares*/lugares,
				/*npcs*/npcs,
				/*conexiones*/conexiones);
		ubicacionesMap.put(ubi.getNombre(), ubi);
		return ubi;
	}

	private List<Conexion> deserealizarConexiones(JsonArray array) {
		Type listType = new TypeToken<ArrayList<Conexion>>() {}.getType();
		return new Gson().fromJson(array, listType);
		/*List<Conexion> conexiones = new ArrayList<Conexion>();
		Gson g = new Gson();
		for(int i = 0; i < array.size() ; i++) {
			JsonObject jo = array.get(i).getAsJsonObject();
			String destino = jo.get("ubicacionDestino").getAsString();
			JsonElement jeObstaculo = jo.get("obstaculo");
			String obstaculo = jeObstaculo.toString();
			conexiones.add( new Conexion(g.fromJson(jo.get("direccion"), Direccion.class), null, obstaculo ));
		}
		return conexiones;*/

		/*JsonDeserializer<Conexion> deserializationConexion = new JsonDeserializer<Conexion>() {

			@Override
			public Conexion deserialize(JsonElement je, Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				return deserealizarConexion(je.getAsJsonObject());
			}
			
		};
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Conexion>>() {}.getType();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Conexion.class, deserializationConexion);
		Gson gson1 = gsonBuilder.create();
		ArrayList<Conexion> conexiones = gson1.fromJson(array, listType);
		return conexiones;*/
	}

	protected Conexion deserealizarConexion(JsonObject jo) {
		Gson gson = new Gson();
		return new Conexion(
				/*direccion*/gson.fromJson(jo, Direccion.class),
				/*ubicacionDestino*/null,//ubicacionesMap.get(jo.get("ubicacionDestino")),
				/*obstaculo*/jo.get("obstaculo").getAsString()
				);
	}

	private List<Npc> deserealizarNpcs(JsonArray array) {
		Type listType = new TypeToken<ArrayList<Npc>>() {}.getType();
		return new Gson().fromJson(array, listType);
	}

	protected List<Lugar> deserializeLugaresObjeto(JsonArray array) {
		List<Lugar> lugares = new ArrayList<Lugar>();
		JsonDeserializer<Lugar> deserializationPerson = new JsonDeserializer<Lugar>() {

			@Override
			public Lugar deserialize(JsonElement je, Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				return deserializeLugar(je);
			}

		};

		Gson gsonType = new GsonBuilder().registerTypeAdapter(Lugar.class, deserializationPerson).create();

		for (int i = 0; i < array.size(); i++) {
			lugares.add(gsonType.fromJson(array.get(i), Lugar.class));
		}
		return lugares;
	}

	protected Lugar deserializeLugar(JsonElement je) {
		JsonObject jo = je.getAsJsonObject();
		List<Item> items = new ArrayList<Item>();
		JsonArray array = jo.get("items").getAsJsonArray();
		for (int i = 0; i < array.size(); i++) {
			String nameItem = array.get(i).getAsString();
			items.add(itemsMap.get(nameItem));
		}
		return new Lugar(/* nombre */jo.get("nombre").getAsString(),
				/* segundoNombre */jo.get("segundoNombre").getAsString(),
				/* genero */new Gson().fromJson(jo.get("genero"), Genero.class),
				/* numero */new Gson().fromJson(jo.get("numero"), Numero.class), /* items */items,
				/* triggers */new ArrayList<Trigger>(), /* descripcion */jo.get("descripcion").getAsString());
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

	public Aventura getAventura() {
		return aventura;
	}

	public static void main(String[] args) {
		ManejoArchivos m = new ManejoArchivos();
		 System.out.println(m.getAventura());
		
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
