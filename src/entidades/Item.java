package entidades;

import java.util.List;

public class Item {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<String> acciones;
	private List<String> efectosSobre;
	//private boolean usado;

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
	
	public void realizarAccion() {
		// Deberia buscar en la lista de acciones y/o efectos sobre respecto a que va a
		// actuar,

	}

}
