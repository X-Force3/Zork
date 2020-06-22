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

public class ManejoArchivos {

	private static final String RUTA = "recursos/";
	private static final String AVENTURA_JSON = RUTA + "aventura.txt";
	
	private Map<String, Ubicacion> ubicacionesMap = new HashMap<String, Ubicacion>();
	private Map<String, Item> itemsMap = new HashMap<String,Item>();
	private Aventura aventura;

	public ManejoArchivos() {
		/*Gson gson = new Gson(); 
		JsonReader jr = new JsonReader(jsonText.trim()); 
		jr.setLenient(true); 
		Map keyValueMap = (Map) gson.fromJson(jr, Object.class);*/
		
		/*JsonDeserializer<Aventura> deserializeAventura = new JsonDeserializer<Aventura>() {

			@Override
			public Aventura deserialize(JsonElement jsonElement, Type typeOf, JsonDeserializationContext arg2)
					throws JsonParseException {
				return construir(jsonElement.getAsJsonObject());
			}
			
		};
		
		aventura = new GsonBuilder()
				.registerTypeAdapter(Aventura.class, deserializeAventura)
				.create()
				.fromJson(jsonText, Aventura.class);*/
		
		JsonObject o = convertFileToJSON(AVENTURA_JSON);
		aventura = construir(o);
		
	}
	
	protected Aventura construir(JsonObject jo) {
		Gson gson = new Gson();
		Configuracion config = gson.fromJson(jo.get("configuracion"), Configuracion.class);
		
		//items
		Type listTypeItem = new TypeToken<ArrayList<Item>>() {}.getType();
		ArrayList<Item> items = new Gson().fromJson(jo.get("items"), listTypeItem);
		for(Item item : items) {
			itemsMap.put(item.getNombre(), item);
		}
		
		//ubicaciones
		Type listTypeUbicaciones = new TypeToken<ArrayList<Ubicacion>>() {}.getType();
		JsonDeserializer<Ubicacion> desUbicacion = new JsonDeserializer<Ubicacion>() {

			@Override
			public Ubicacion deserialize(JsonElement jsonElement, Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				
				JsonObject jo = jsonElement.getAsJsonObject();
				List<Lugar> lugares = deserializeLugares(jo.get("lugares").getAsJsonArray());
				
				/*List<Item> items = new ArrayList<Item>();
				JsonArray array = jsonObject.get("items").getAsJsonArray();
				for(int i = 0; i < array.size(); i++) {
					String nombreItem = array.get(i).getAsString();
					items.add(itemsMap.get(nombreItem));
				}*/
				String nani = jo.get("descripcion").getAsString();
				
				return new Ubicacion(
						jo.get("nombre").getAsString(),
						new Gson().fromJson(jo.get("genero"),Genero.class),
						new Gson().fromJson(jo.get("numero"),Numero.class),
						jo.get("descripcion").getAsString(),
						lugares
				);
			}
			
		};
		GsonBuilder gsonBuilder= new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Ubicacion.class, desUbicacion);
		Gson gson1=gsonBuilder.create();

		System.out.println("Ubis: ");
		ArrayList<Ubicacion> ubicacionesss = gson1.fromJson(jo.get("ubicaciones"), listTypeUbicaciones);
		for (Ubicacion ubicacionesss2 : ubicacionesss) {
			System.out.println(ubicacionesss2.getNombre());
		}
		
		return new Aventura(config, new ArrayList<>());
	}
	
	/*@Override
	public Ubicacion deserialize(JsonElement jsonElement, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		List<Item> items = new ArrayList<Item>();
		JsonArray array = jsonObject.get("items").getAsJsonArray();
		for(int i = 0; i < array.size(); i++) {
			String nombreItem = array.get(i).getAsString();
			items.add(itemsMap.get(nombreItem));
		}
		return new Ubicacion("nani","nana");
	}*/
	
	protected List<Lugar> deserializeLugares(JsonArray array) {
		// [barreta, espejo, ...]
		List<Lugar> lugares = new ArrayList<Lugar>();
		JsonDeserializer<Lugar> deserializationPerson = new JsonDeserializer<Lugar>() {

			@Override
			public Lugar deserialize(JsonElement je, Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				return deserializeLugar(je);
			}
			
		};
		for(int i = 0; i < array.size(); i++) {
			//todo
		}
		return lugares;
	}

	protected Lugar deserializeLugar(JsonElement je) {
		JsonObject jo = je.getAsJsonObject();
		List<Item> items = new ArrayList<Item>();
		JsonArray array = jo.get("items").getAsJsonArray();
		for(int i = 0; i< array.size(); i++) {
			String nameItem = array.get(i).getAsString();
			items.add(itemsMap.get(nameItem));
		}
		return new Lugar(
				/*nombre*/jo.get("nombre").getAsString(), 
				/*segundoNombre*/jo.get("segundoNombre").getAsString(),
				/*genero*/new Gson().fromJson(jo.get("genero"),Genero.class),
				/*numero*/new Gson().fromJson(jo.get("numero"),Numero.class),
				/*items*/items,
				/*triggers*/new ArrayList<Trigger>(),
				/*descripcion*/jo.get("descripcion").getAsString()
				);
	}

	public static JsonObject convertFileToJSON (String path){

        // Read from File to String
        JsonObject jsonObject = new JsonObject();
        
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(path));
            jsonObject = jsonElement.getAsJsonObject();
        } catch (FileNotFoundException e) {
           
        } catch (IOException ioe){
        
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
		//System.out.println(m.getAventura());
		//System.out.println(m.getItemsMap().get("espejo"));
	}
}
