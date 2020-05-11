package entidades;

import java.util.List;

public class Npc {

	private String nombre;
	private String genero;
	private String numero;
	private String descripcion;
	private String dialogo;
	private List<Object> trigger;
	
	public Npc(String nombre, String genero, String numero, String descripcion, String dialogo, List<Object> trigger) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.descripcion = descripcion;
		this.dialogo = dialogo;
		this.trigger = trigger;
	}

}
