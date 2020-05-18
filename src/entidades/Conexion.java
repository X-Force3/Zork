package entidades;

public class Conexion {

	private Direccion direccion;
	private String locacionDestino;
	private String obstaculo;
	
	/// Podria haber un ID para asi diferenciar los puntos cardinales de una misma o
	/// distinta ubicacion.

	public Conexion(Direccion direccion, String locacionDestino, String obstaculo) {
		super();
		this.direccion = direccion;
		this.locacionDestino = locacionDestino;
		this.obstaculo = obstaculo;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}

	public String getLocacionDestino() {
		return locacionDestino;
	}

	public String getObstaculo() {
		return obstaculo;
	}

	@Override
	public String toString() {
		return "Conexion [direccion=" + direccion + ", locacionDestino=" + locacionDestino + ", obstaculo=" + obstaculo
				+ "]";
	}

}
