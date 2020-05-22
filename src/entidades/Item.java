package entidades;

import java.util.List;

public class Item {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<String> acciones;
	private List<String> efectosSobre;
	//private boolean usado; 
	// Luz: puede ser que las acciones y los efectos sean enums?

	public Item(String nombre, Genero genero, Numero numero, List<String> acciones, List<String> efectosSobre) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.acciones = acciones;
		this.efectosSobre = efectosSobre;
	}

	public String getNombre() {
		return nombre;
	}

	public Genero getGenero() {
		return genero;
	}

	public Numero getNumero() {
		return numero;
	}

	public List<String> getAcciones() {
		return acciones;
	}

	public List<String> getEfectosSobre() {
		return efectosSobre;
	}

	public boolean realizarAccion(Npc npc, String accion) {

		boolean res = false;
		for (String elemento : this.efectosSobre) {

			if (elemento.equals(npc.getNombre())) {
				for (String elemento2 : this.acciones) {

					if (elemento2.contentEquals(accion)) {
						res = true;
					}
				}
			}
		}

		return res;
	}

	public boolean realizarAccion(Lugar lugar, String accion) {

		boolean res = false;
		for (String elemento : efectosSobre) {

			if (elemento.equals(lugar.getNombre())) {
				for (String elemento2 : this.acciones) {

					if (elemento2.contentEquals(accion)) {
						res = true;
					}
				}
			}
		}

		return res;
	}

	@Override
	public String toString() {
		return "Item [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", acciones=" + acciones
				+ ", efectosSobre=" + efectosSobre + "]";
	}

}
