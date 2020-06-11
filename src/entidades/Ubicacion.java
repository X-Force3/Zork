package entidades;

import java.util.ArrayList;
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

	public String describirUbicacion() {
		String cadena = this.descripcion + this.lugares.get(0).describirObjetosDisponibles() + " "
				+ this.npcs.get(0).conjugarNpc() + this.conexiones.get(0).conjugarConexion();
		// arreglar como mostrar si la ubicacion no tiene items o lugares o conexiones
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

	public List<Item> getItems() {
		List<Item> itemsUbicacion = new ArrayList<Item>();

		for (Lugar lugar : this.lugares) {
			for (Item item : lugar.getItems())
				itemsUbicacion.add(item);
		}
		return itemsUbicacion;
	}

	public void eliminarItemUbicacion(Item item) {
		for (Lugar lugar : this.lugares) {
			lugar.eliminarItemLugar(item);
		}
	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ubicacion other = (Ubicacion) obj;
		if (other.nombre.equals(this.nombre))
			return true;
		return false;
	}

}
