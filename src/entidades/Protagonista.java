package entidades;

import java.util.List;

public class Protagonista {

	private String nombre;
	private List<Item> inventario;

	public Protagonista() {

	}

	public boolean añadirItem(Item item) {
		return false;

	}

	// Quizas deberia devolver una String, siendo esta la respuesta del texto
	// enviado
	public void hablar(String dialogo) {

	}

	public void utilizarItem(Item item) {
		//item.realizarAccion();
	}

///Estaria bueno que se pueda especificar donde usar el item, asi dentro de la class item se podria	
// buscar si ese objetivo esta dentro de los limites que generarian un trigger en el destino
// pienso que podria implicar metodos como estos, o una clase abstracta que este por encima de todo lo "interactuable"

//	public void utilizarItem(Item item, Lugar destino) {
//		item.realizarAccion();
//	}
//	
//	public void utilizarItem(Item item, Lugar NPC) {
//		item.realizarAccion();
//	}

	/// El protagonista pide si puede desplazarse a un place, o a una conexion
	/// dentro de la ubicacion
	public boolean desplazarse(Lugar lugarDestino) {
		return false;

	}

	public boolean desplazarse(Conexion conexionDestino) {
		return false;

	}
}
