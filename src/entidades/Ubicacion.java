package entidades;

import java.util.List;

public class Ubicacion {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<Lugar> lugares;
	private List<Npc> npcs;
	private List<Conexion> conexiones;
	
	public Ubicacion(String nombre, Genero genero, Numero numero, List<Lugar> lugares, List<Npc> npcs,
			List<Conexion> conexiones) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.lugares = lugares;
		this.npcs = npcs;
		this.conexiones = conexiones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreUbicacion) {
		nombre = nombreUbicacion;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public Numero getNumero() {
		return numero;
	}

	public List<Lugar> getLugares() {
		return lugares;
	}

	public List<Npc> getNpcs() {
		return npcs;
	}

	public List<Conexion> getConexiones() {
		return conexiones;
	}

	/// Como se describiria? No deberia ser un atributo dentro de la class para asi
	/// poder instanciar esa descripcion?
	public void describirUbicacion() {

	}
	/// Deberia tener metodos donde pueda comprobar si los npc/ lugares/ o
	/// conexiones figuran dentro de lo disponible dentro de la ubicacion


	@Override
	public String toString() {
		return "Ubicacion [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", lugares=" + lugares
				+ ", npcs=" + npcs + ", conexiones=" + conexiones + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ubicacion other = (Ubicacion) obj;
		if (conexiones == null) {
			if (other.conexiones != null)
				return false;
		} else if (!conexiones.equals(other.conexiones))
			return false;
		if (genero != other.genero)
			return false;
		if (lugares == null) {
			if (other.lugares != null)
				return false;
		} else if (!lugares.equals(other.lugares))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (npcs == null) {
			if (other.npcs != null)
				return false;
		} else if (!npcs.equals(other.npcs))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
	
	
}
