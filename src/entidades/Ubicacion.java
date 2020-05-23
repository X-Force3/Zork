package entidades;

import java.util.List;

public class Ubicacion {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private String descripcion;
	private List<Lugar> lugares;
	private List<Npc> npcs;
	private List<Conexion> conexiones;

	public Ubicacion() {
	}

	public Ubicacion(String nombre, Genero genero, Numero numero, String descripcion, List<Lugar> lugares,
			List<Npc> npcs, List<Conexion> conexiones) {
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.descripcion = descripcion;
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

	// Estás en un muelle. En el suelo hay una barreta, un rociador con cerveza de
	// raiz y un espejo.
	// Hay un pirata fantasma.
	// Al sur se puede ir hacia una taberna.
	public String describirUbicacion() {

		String cadena = this.descripcion + this.lugares.get(0).describirObjetosDisponibles() + " "
				+ this.npcs.get(0).conjugarNpc() + this.conexiones.get(0).conjugarConexion();

		return cadena;
	}

	public String conjugarUbicacion() {
		String articulo = "";
		if (this.genero == Genero.FEMALE) {
			articulo = this.numero == Numero.SINGULAR ? "una" : "las";
		} else {
			articulo = this.numero == Numero.SINGULAR ? "un" : "los";
		}
		return articulo + " " + this.nombre;
	}

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
