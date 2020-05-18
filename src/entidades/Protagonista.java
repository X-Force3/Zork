package entidades;

import java.util.ArrayList;
import java.util.List;

public class Protagonista {

	private String nombre;
	private List<Item> inventario;
	private Ubicacion ubicacion;
	
	
	public Protagonista() {
		nombre = "anonimo";
		inventario = new ArrayList<Item>();
		List<Lugar> lugares =new ArrayList<Lugar>();
		
		lugares.add(new Lugar());
		ubicacion = new Ubicacion("Casa",Genero.FEMALE,Numero.SINGULAR,lugares, null, null);
	}
	
	public Protagonista(String nombreJugador) {
		nombre = nombreJugador;
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
			
			case "¡No hay nada que me digas que me haga cambiar de opinión!" :
				respuesta = "Tengo que ir por otro camino";
			break;
			
			case "Es la segunda ves que venís. Es hora de hablar sobre tus inquietudes..." :
				respuesta = "Estoy buscando...";
				
			default : respuesta = "No te entiendo";
		}
		
		return respuesta;
	}

	public void utilizarItem(Item item) {
		
		item.realizarAccion();
		///si se utiliza el item, ¿lo saco del inventario?
		///inventario.remove(item);
	}

///Estaria bueno que se pueda especificar donde usar el item, asi dentro de la class item se podria	
// buscar si ese objetivo esta dentro de los limites que generarian un trigger en el destino
// pienso que podria implicar metodos como estos, o una clase abstracta que este por encima de todo lo "interactuable"

	/// El protagonista pide si puede desplazarse a un place, o a una conexion
	/// dentro de la ubicacion
	public boolean desplazarse(Lugar lugarDestino) {
		
		
		
		return false;

	}

	public boolean desplazarse(Conexion conexionDestino) {
		return false;

	}
}
