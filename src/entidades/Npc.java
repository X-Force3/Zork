package entidades;

import java.util.List;

public class Npc {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private String descripcion;
	private String dialogo;
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
	}

}
