package entidades;

import java.util.ArrayList;
import java.util.List;

public class Protagonista {

	private String nombre;
	private List<Item> inventario;
	private Ubicacion ubicacionActual;
	
	public Protagonista(String nombreJugador) {
	
			nombre = nombreJugador;
			inventario = new ArrayList<Item>();
			///todo el resto es para poner la ubicacion actual
			List<Lugar> lugares =new ArrayList<Lugar>();

			List<String> efectosItemsSuelo = new ArrayList<String>();
			efectosItemsSuelo.add("npcs");
			efectosItemsSuelo.add("item");
			efectosItemsSuelo.add("self");

			List<String> accionItemsSuelo = new ArrayList<String>();
			accionItemsSuelo.add("usar");

			Item barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
			Item espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
			Item rociadorCervezaRaiz = new Item("rociador con cerveza de raíz", Genero.MALE, Numero.SINGULAR,
			accionItemsSuelo, efectosItemsSuelo);
			List<Item> itemsSuelo = new ArrayList<Item>();
			itemsSuelo.add(barreta);
			itemsSuelo.add(rociadorCervezaRaiz);
			itemsSuelo.add(espejo);
			///creado lista de items, se crea el lugar
			lugares.add(new Lugar("Casa",Genero.FEMALE,Numero.SINGULAR,itemsSuelo));
			///lugares HECHO - Falta conexiones

			//Para crear una conexion -->
			Ubicacion taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR,"Taberna",lugares, null, null);
			Conexion surMuelle = new Conexion(Direccion.SUR, taberna, new Npc("Messi", Genero.MALE, Numero.SINGULAR, "Messi, futbolista", "Hola, soy messi", null,false));
			List<Conexion> conexionesMuelle = new ArrayList<Conexion>();
			conexionesMuelle.add(surMuelle);
			//En este caso, se crea la ubicacion "Casa" con los mismos parametros que "Taberna"
			ubicacionActual = new Ubicacion("Casa",Genero.FEMALE,Numero.SINGULAR,"Casa",lugares, null, conexionesMuelle);
	}
	
	public boolean añadirItem(Item item) {
		boolean itemAñadido = false;
		
		if(inventario.contains(item) == false) {
			inventario.add(item);
			itemAñadido = true;
		}
		
		return itemAñadido;

	}

	public String hablar(String dialogo) {

		String respuesta;
		
		switch(dialogo) {
			case "Presentacion" :
				respuesta = "Hola! Mi nombre es " + nombre;
			break;
			
			case "¡No hay nada que me digas que me haga cambiar de opinión!" :
				respuesta = "Tengo que ir por otro camino";
			break;
			
			case "Es la segunda ves que venís. Es hora de hablar sobre tus inquietudes..." :
				respuesta = "Estoy buscando...";
			break;
			
			case "Hola, soy messi" : 
				respuesta = "Hola messi!";
			break;
			
			default : respuesta = "No te entiendo";
		}
		
		return respuesta;
	}

	public void utilizarItem(Item item, Npc npc, String accion) {
		
		item.realizarAccion(npc, accion);
		///si se utiliza el item, ¿lo saco del inventario?
		inventario.remove(item); 
	}

	/// El protagonista pide si puede desplazarse a un place, o a una conexion
	/// dentro de la ubicacion
	public boolean desplazarse(Ubicacion lugarDestino) {
		
		if(this.ubicacionActual.equals(lugarDestino))
			return false;
		ubicacionActual = lugarDestino;
		return true;
	}

	public boolean desplazarse(Conexion conexionDestino) {
		boolean res= false;
		
		for(Conexion c : ubicacionActual.getConexiones()) {
			
			if(c.equals(conexionDestino)) {
				ubicacionActual = conexionDestino.getUbicacionDestino();
				return true;
			}
		}
		return res;
	}
}
