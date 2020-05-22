package entidades;

import java.util.List;

public class Npc {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private String descripcion;
	private String dialogo;// private List<String> dialogos;
	private List<Object> trigger;
	private boolean condicionDeObstaculo;

	public Npc(String nombre, Genero genero, Numero numero, String descripcion, String dialogo, List<Object> trigger,
			boolean condicionDeObstaculo) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.descripcion = descripcion;
		this.dialogo = dialogo;
		this.trigger = trigger;
		this.condicionDeObstaculo = condicionDeObstaculo;
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

	public String getDescripcion() {
		return descripcion;
	}

	public String getDialogo() {
		return dialogo;
	}

	public List<Object> getTrigger() {
		return trigger;
	}

	public boolean getCondicionDeObstaculo() {
		return this.condicionDeObstaculo;
	}

	public void presentarse() {
		/// De donde saldria el texto que lo presente? Podria ser la variable
		/// descripcion, o necesitar de otra variable
	}

	public void hablar() {
		/// Buscaria algo en la lsita dialogos para responder acorde a la pregunta, o a
		/// lo que necesite decir
	}

	@Override
	public String toString() {
		return "Npc [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", descripcion=" + descripcion
				+ ", dialogo=" + dialogo + ", trigger=" + trigger + "]";
	}

}
