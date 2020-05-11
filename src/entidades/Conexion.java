package entidades;

import java.util.List;

public class Conexion {

	private String direccion;
	private String locacionDestino;
	private List<String> obstaculos;
	
	public Conexion(String direccion, String locacionDestino, List<String> obstaculos) {
		super();
		this.direccion = direccion;
		this.locacionDestino = locacionDestino;
		this.obstaculos = obstaculos;
	}
	
}
