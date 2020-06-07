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
	
	public List<Item> getItems(){
		// deberia devolver una lista con todos los items de la ubicacion
		return null;
	}	
	
	public void eliminarItem(Item item) {
		// buscar cual es el lugar que tiene el item, y dereferenciarlo (creo que quitandolo de la lista ya esta)
		
	}
	
	

}
