package entidades;

import java.util.List;

public class Lugar {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<String> items;
	
	public Lugar() {

	}
	
	//Luciano: Agrego constructor 
	public Lugar(String nombre, Genero genero, Numero numero, List<String> items) {
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.items = items;
	}
	
	//Luciano: Agrego gete nombre
	public String getNombre() {
		return this.nombre;
	}
	
	

	/// Se recorre la collection
	public void describirObjetosDisponibles() {

	}

	@Override
	public String toString() {
		return "Lugar [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", items=" + items + "]";
	}

}
