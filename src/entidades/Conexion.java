package entidades;

import java.util.List;

public class Conexion {

	private Direccion direccion;
	private String locacionDestino;
	private List<String> obstaculos;
	
	public Conexion(Direccion direccion, String locacionDestino, List<String> obstaculos) {
	/// Podria haber un ID para asi diferenciar los puntos cardinales de una misma o
	/// distinta ubicacion.

	public Conexion(String direccion, String locacionDestino, List<String> obstaculos) {
		super();
		this.direccion = direccion;
		this.locacionDestino = locacionDestino;
		this.obstaculos = obstaculos;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public String getLocacionDestino() {
		return locacionDestino;
	}

	public List<String> getObstaculos() {
		return obstaculos;
	}
	
}
