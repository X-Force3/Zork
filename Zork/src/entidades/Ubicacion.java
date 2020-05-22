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

	
}