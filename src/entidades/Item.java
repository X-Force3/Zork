package entidades;

import java.util.List;

public class Item {

	private String nombre;
	private String genero;
	private String numero;
	private List<String> acciones;
	private List<String> efectosSobre;

	private boolean usado;

	public Item(String nombre, String genero, String numero, List<String> acciones, List<String> efectosSobre) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.acciones = acciones;
		this.efectosSobre = efectosSobre;
	}

	public void realizarAccion() {
		// Deberia buscar en la lista de acciones y/o efectos sobre respecto a que va a
		// actuar,

	}

}
