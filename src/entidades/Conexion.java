package entidades;

public class Conexion {

	private Direccion direccion;
	private Ubicacion ubicacionDestino;
	private String obstaculo;

	/// Podria haber un ID para asi diferenciar los puntos cardinales de una misma o
	/// distinta ubicacion.

	public Conexion(Direccion direccion, Ubicacion locacionDestino, String obstaculo) {
		super();
		this.direccion = direccion;
		this.ubicacionDestino = locacionDestino;
		this.obstaculo = obstaculo;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public Ubicacion getUbicacionDestino() {
		return ubicacionDestino;
	}

	public String getObstaculo() {
		return obstaculo;
	}

	@Override
	public String toString() {
		return this.direccion + " :" + " locacionDestino ";
	}

	public String conjugarConexion() {
		return "Al " + this.direccion.getNombre() + " se puede ir hacia " + this.ubicacionDestino.conjugarUbicacion()
				+ ".";
	}

}
