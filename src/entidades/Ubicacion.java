package entidades;

import java.util.List;

public class Ubicacion {

	private String nombre;
	private String genero;
	private String numero;
	private List<Lugar> lugaresDisponibles;
	private List<String> npcsDisponibles;
	private List<Conexion> conexionesDisponibles;

	public Ubicacion(String nombre, String genero, String numero, List<Lugar> lugaresDisponibles,
			List<String> npcsDisponibles, List<Conexion> conexionesDisponibles) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.lugaresDisponibles = lugaresDisponibles;
		this.npcsDisponibles = npcsDisponibles;
		this.conexionesDisponibles = conexionesDisponibles;
	}

	/// Como se describiria? No deberia ser un atributo dentro de la class para asi
	/// poder instanciar esa descripcion?
	public void describirUbicacion() {

	}

	/// Deberia tener metodos donde pueda comprobar si los npc/ lugares/ o
	/// conexiones figuran dentro de lo disponible dentro de la ubicacion

}
