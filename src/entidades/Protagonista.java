package entidades;

import java.util.ArrayList;
import java.util.List;

public class Protagonista {

	private String nombre;
	private List<Item> inventario;
	private Ubicacion ubicacionActual;
	private boolean estaVivo;

	public Protagonista(String nombreJugador, Ubicacion ubicacionInicial) {
		nombre = nombreJugador;
		ubicacionActual = ubicacionInicial;
		inventario = new ArrayList<Item>();
		estaVivo = true;
	}

	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void morir() {
		this.estaVivo = false;
	}
	
	public Ubicacion getUbicacionActual() {
		return this.ubicacionActual;
	}

	public boolean a�adirItem(Item item) {
		boolean itemA�adido = false;

		if (inventario.contains(item) == false) {
			inventario.add(item);
			itemA�adido = true;
		}

		return itemA�adido;
	}

	public String hablar(String dialogo) {

		String respuesta;

		switch (dialogo) {
		case "Presentacion":
			respuesta = "Hola! Mi nombre es " + nombre;
			break;

		case "�No hay nada que me digas que me haga cambiar de opini�n!":
			respuesta = "Tengo que ir por otro camino";
			break;

		case "Es la segunda ves que ven�s. Es hora de hablar sobre tus inquietudes...":
			respuesta = "Estoy buscando...";
			break;

		case "Hola, soy Messi. ":
			respuesta = "Hola Messi!";
			break;

		default:
			respuesta = "No te entiendo...";
		}

		return respuesta;
	}

	/// El protagonista pide si puede desplazarse a un place, o a una conexion
	/// dentro de la ubicacion
//	public boolean desplazarse(Ubicacion ubicacionDestino) {
//
//		for (Conexion conexion : this.getUbicacionActual().getConexiones()) {
//			if (conexion.getUbicacionDestino().equals(ubicacionDestino)) {
//				this.ubicacionActual = ubicacionDestino;
//				return true;
//			}
//		}
//
//		return false;
//	} No se utiliza en la clase Aventura el desplazarse seg�n la Ubicacion, pero s� el desplazarse seg�n la Conexion.

	/*public void desplazarse(String nombreUbicacionDestino, List<Ubicacion> ubicaciones) {
		AnalizadorDeTexto analizador = new AnalizadorDeTexto();
		Ubicacion ubicacionDestino = analizador.devolverObjetoUbicacion(nombreUbicacionDestino, ubicaciones);
		this.ubicacionActual = ubicacionDestino;
	}*/
	
	public void desplazarse(Ubicacion ubicacionDestino) {
		this.ubicacionActual = ubicacionDestino;
	}

	public void eliminarItem(Item item) { // al accionar el trigger, deberiamos eliminar el item del inventario
		this.inventario.remove(item);
	}

	public List<Item> getInventario() {
		return this.inventario;
	}

	public String describirInventario() {

		String queHay = "";
		if (inventario.isEmpty())
			queHay = "no hay nada. Est� vac�o...";
		else {
			if (inventario.size() == 1) {
				queHay = "hay " + inventario.get(0).conjugarItem();
			} else {
				for (int i = 0; i < inventario.size() - 2; i++) {
					queHay = queHay + inventario.get(i).conjugarItem() + ", ";
				}
				queHay = "hay " + queHay + inventario.get(inventario.size() - 2).conjugarItem() + " y "
						+ inventario.get(inventario.size() - 1).conjugarItem();
			}
		}
		return "En tu inventario " + queHay + ".";
	}

	public String getNombre() {
		return nombre;
	}
}
