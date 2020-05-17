package entidades;

import java.util.List;

public class Lugar {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<String> items;
	
	public Lugar() {

	}

	/// Se recorre la collection
	public void describirObjetosDisponibles() {

	}

	@Override
	public String toString() {
		return "Lugar [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", items=" + items + "]";
	}

}
