package entidades;

import java.util.List;

public class Conexion {

	private Direccion direccion;
	private String ubicacionDestino;
	private String obstaculo;

	/// Podria haber un ID para asi diferenciar los puntos cardinales de una misma o
	/// distinta ubicacion.

	public Conexion(Direccion direccion, String locacionDestino, String obstaculo) {
		super();
		this.direccion = direccion;
		this.ubicacionDestino = locacionDestino;
		this.obstaculo = obstaculo;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public String getUbicacionDestino() {
		return ubicacionDestino;
	}

	public String getObstaculo() {
		return obstaculo;
	}

	public String conjugarConexion(List<Ubicacion> ubicaciones) {
		AnalizadorDeTexto analizador = new AnalizadorDeTexto();
		Ubicacion ubicacion = analizador.devolverObjetoUbicacion(this.ubicacionDestino, ubicaciones);
		return "Al " + this.direccion.getNombre() + " se puede ir hacia " + ubicacion.conjugarUbicacion()
				+ ".";
	}

	@Override
	public String toString() {
		return "Conexion [dir=" + direccion + ", ubiDestino=" + ubicacionDestino + ", obstaculo="
				+ obstaculo + "]";
	}

}
