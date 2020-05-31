package entidades;

import java.util.ArrayList;
import java.util.List;

public class Protagonista {

	private String nombre;
	private List<Item> inventario;
	private Ubicacion ubicacionActual;
	
	public Protagonista(String nombreJugador, Ubicacion ubicacionInicial) {
		nombre = nombreJugador;
		ubicacionActual = ubicacionInicial;
		inventario = new ArrayList<Item>();
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
		
		if(ubicacionActual.getNombre() == lugarDestino.getNombre())
			return false;
		ubicacionActual = lugarDestino;
		return true;
	}

	public boolean desplazarse(Conexion conexionDestino) {
		boolean res= false;
		String nombreDestino = conexionDestino.getUbicacionDestino().getNombre();
		
		for(Conexion c : ubicacionActual.getConexiones()) {
			
			if(c.getUbicacionDestino().getNombre() == nombreDestino) {
				ubicacionActual = conexionDestino.getUbicacionDestino();
				return true;
			}
		}
		return res;
	}
}
