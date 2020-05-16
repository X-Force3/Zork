package entidades;

import java.util.List;

public class Aventura {

	private String titulo;
	private String mensajeDeBienvenida;
	protected Ubicacion ubicacionInicial;
	protected List<Ubicacion> ubicacionesDisponibles;

	public Aventura() {

	}

	public void comenzar() {

	}

	// Metodo no necesario para esta entrega
	public boolean cargarAventura(String pathFile) {
		return false;

	}
}
