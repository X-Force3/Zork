package entidades;

import java.util.List;

public class Conexion {

	private Direccion direccion;
	private String ubicacionDestino;
	private String obstaculo;

	public Conexion() {
		this.ubicacionDestino = " ";
	}
	
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
	
	public String describirConexiones(List<Ubicacion> ubicaciones) {
		String descripcion = "";
		for (Ubicacion ubicacion : ubicaciones) {
			descripcion += conjugarConexion(ubicacion) + "\n";
		}
		return descripcion;
	}
	
	public String conjugarConexion(Ubicacion ubicacion) {
		return "Al " + direccion.getNombre() + " se puede ir hacia " + ubicacion.conjugarUbicacion()
				+ ".";
	}

	@Override
	public String toString() {
		return "Conexion [dir=" + direccion + ", ubiDestino=" + ubicacionDestino + ", obstaculo="
				+ obstaculo + "]";
	}

}
