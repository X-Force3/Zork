package entidades;

import java.util.List;

public class Npc {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private String descripcion;
	private String dialogo;
	private List<Trigger> trigger;
	
	public Npc(String nombre, Genero genero, Numero numero, String descripcion, String dialogo, List<Trigger> trigger) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.descripcion = descripcion;
		this.dialogo = dialogo;
		this.trigger = trigger;
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
	
//	public List<Object> getTrigger() {
//		return trigger;
//	}

	public String presentarse() { // cambiar diag de clase
		return this.descripcion;
	}

	public String hablar() {	// cambiar diag de clase
		return this.dialogo;
	}
		
	public String verificarTrigger(Item item) {	
		for(Trigger elemento : this.trigger) {
			if(elemento.getType() == "item" && item.getNombre() == elemento.getThing()) {
				this.ejecutarTrigger(elemento);
				return elemento.getOn_trigger();
			}
		}
		return "";
	}
	
	public void ejecutarTrigger(Trigger trigger) {
		if(trigger.getAfter_trigger() == "remove")
			this.nombre = "";	// le quita el nombre al npc, para no mostrarlo mas, y para que, si era un obstaculo de una coneccion, ya no lo encuentr
		// este else if, podria ser otra ejemplo de que accion puede realizar ese trigger
//		else if(trigger.getAfter_trigger() == "bajar vida") {
//			this.vida -=50;
//		}
	}
	
	

	@Override
	public String toString() {
		return "Npc [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", descripcion=" + descripcion
				+ ", dialogo=" + dialogo + "]"; // agregar trigger, lo borre para la primera entrega
	}

}