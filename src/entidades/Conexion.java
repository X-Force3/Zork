package entidades;

public class Conexion {

	private Direccion direccion;
	private Ubicacion ubicacionDestino;
	private Npc obstaculo;

	/// Podria haber un ID para asi diferenciar los puntos cardinales de una misma o
	/// distinta ubicacion.

	public Conexion(Direccion direccion, Ubicacion locacionDestino, Npc obstaculo) {
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

	public Npc getObstaculo() {
		return obstaculo;
	}

	@Override
	public String toString() {
		return this.direccion + " :" + " locacionDestino ";
	}

	public Ubicacion irHaciaDestino() {
		if (obstaculo.getCondicionDeObstaculo()) {
			return this.ubicacionDestino;
		} else
			return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conexion other = (Conexion) obj;
		if (direccion != other.direccion)
			return false;
		if (locacionDestino == null) {
			if (other.locacionDestino != null)
				return false;
		} else if (!locacionDestino.equals(other.locacionDestino))
			return false;
		if (obstaculo == null) {
			if (other.obstaculo != null)
				return false;
		} else if (!obstaculo.equals(other.obstaculo))
			return false;
		return true;
	}
	
	

}
