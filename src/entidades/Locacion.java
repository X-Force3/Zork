package entidades;

import java.util.List;

public class Locacion {

	private String nombre;
	private String genero;
	private String numero;
	private List<Lugar> lugares;
	private List<String> npcs;
	private List<Conexion> conexiones;
	
	public Locacion(String nombre, String genero, String numero, List<Lugar> lugares, List<String> npcs,
			List<Conexion> conexiones) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.lugares = lugares;
		this.npcs = npcs;
		this.conexiones = conexiones;
	}

	public String getGenero() {
		return genero;
	}

	
	
}
