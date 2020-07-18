package entidades;

import java.util.ArrayList;
import java.util.List;

public class Protagonista {

	public static final int VIDA_INICIAL = 6;

	private String nombre;
	private List<Item> inventario;
	private Ubicacion ubicacionActual;
	private boolean estaVivo;
	private int vida = VIDA_INICIAL;

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
		vida = 0;
		this.estaVivo = false;
	}

	public Ubicacion getUbicacionActual() {
		return this.ubicacionActual;
	}

	public boolean anadirItem(Item item) {
		boolean itemAnadido = false;

		if (inventario.contains(item) == false) {
			inventario.add(item);
			itemAnadido = true;
		}

		return itemAnadido;
	}

	public String hablar(String dialogo) {

		String respuesta;

		switch (dialogo) {
		case "Presentacion":
			respuesta = "¡Hola! Mi nombre es " + nombre + ".";
			break;

		case "¡No hay nada que me digas que me haga cambiar de opinión!":
			respuesta = "Tengo que ir por otro camino";
			break;

		case "Es la segunda ves que venís. Es hora de hablar sobre tus inquietudes...":
			respuesta = "Estoy buscando...";
			break;

		case "Hola, soy Messi.":
			respuesta = "¡Hola Messi!";
			break;

		default:
			respuesta = "No te entiendo...";
		}

		return respuesta;
	}

	public void desplazarse(Ubicacion ubicacionDestino) {
		this.ubicacionActual = ubicacionDestino;
	}

	public void eliminarItem(Item item) {
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

	public void lastimar() {
		this.vida -= 2;
		if (this.vida <= 0)
			this.estaVivo = false;
	}

	public void aniadirVida() {
		this.vida += 2;
	}

	public int getVida() {
		return vida;
	}

}
