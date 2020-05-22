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
		List<Conexion> conexiones = new ArrayList<Conexion>();
		
		conexiones.add(new Conexion(Direccion.ESTE,"Muelle",""));
		conexiones.add(new Conexion(Direccion.SUR,"Casa",""));
		conexiones.add(new Conexion(Direccion.OESTE,"Barco Pirata","Minas explosivas"));
		
		lugares.add(new Lugar());
		
		ubicacionActual = new Ubicacion("Casa",Genero.FEMALE,Numero.SINGULAR,lugares, null, conexiones);
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
			
			default : respuesta = "No te entiendo";
		}
		
		return respuesta;
	}

	public void utilizarItem(Item item) {
		
		item.realizarAccion();
		///si se utiliza el item, ¿lo saco del inventario?
		inventario.remove(item);
	}

///Estaria bueno que se pueda especificar donde usar el item, asi dentro de la class item se podria	
// buscar si ese objetivo esta dentro de los limites que generarian un trigger en el destino
// pienso que podria implicar metodos como estos, o una clase abstracta que este por encima de todo lo "interactuable"

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
			
			if(c.equals(conexionDestino))
				return true;
			
		}
		return res;
	}
}
