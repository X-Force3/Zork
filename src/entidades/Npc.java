package entidades;

import java.util.List;

public class Npc {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private String descripcion;
	private List<String> dialogos;
	private List<Object> trigger;
	
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

	public Npc(String nombre, String genero, String numero, String descripcion, List<String> dialogos,
			List<Object> trigger) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.descripcion = descripcion;
		this.dialogos = dialogos;
		this.trigger = trigger;
	}

	public void presentarse() {
		/// De donde saldria el texto que lo presente? Podria ser la variable
		/// descripcion, o necesitar de otra variable
	}

	public void hablar() {
		/// Buscaria algo en la lsita dialogos para responder acorde a la pregunta, o a
		/// lo que necesite decir
	}

}
