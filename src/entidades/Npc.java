package entidades;

import java.util.List;

import javafx.util.Pair;

public class Npc {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private String descripcion;
	private String dialogo;
	private List<Trigger> triggers;

	public Npc() {
		this.nombre = " ";
	}
	
	public Npc(String nombre, Genero genero, Numero numero, String descripcion) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.descripcion = descripcion;
	}

	public void setDialogo(String dialogo) {
		this.dialogo = dialogo;
	}

	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
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

	public List<Trigger> getTrigger() {
		return triggers;
	}

	public String presentarse() { // cambiar diag de clase
		return this.descripcion;
	}

	public String hablar() { // cambiar diag de clase
		return this.dialogo;
	}

	/**
	 * @return pair.second true si se efectu� el trigger
	 * false si no se pudo, item equivocado.
	 * */
	public Pair<String,Boolean> verificarTrigger(Item item, Protagonista protagonista) {
		Trigger lastimar = new Trigger("item", " ", "No realizaste el ataque de manera correcta, el animal te lastimó. Ahora tienes menos puntos de vida...", "lastimar");
		
		for (Trigger elemento : this.triggers) {
			if ( elemento.getType().equals("item") && ( item.getNombre().equals( elemento.getThing() ) ) ) {
				this.ejecutarTrigger(elemento, protagonista);
				protagonista.eliminarItem(item);
				return new Pair<String,Boolean>(elemento.getOn_trigger(),true);
			}
		}
		
		this.ejecutarTrigger(lastimar, protagonista);
		return new Pair<String,Boolean>(lastimar.getOn_trigger(),false);
	}

	public void ejecutarTrigger(Trigger trigger, Protagonista protagonista) {
		if (trigger.getAfter_trigger().equals("remove"))
			this.nombre = "borrado";
		// le quita el nombre al npc, para no mostrarlo mas, y para que, si era un
		// obstaculo de una coneccion, ya no lo encuentr
		// este else if, podria ser otra ejemplo de que accion puede realizar ese
		// trigger
		else if(trigger.getAfter_trigger().equals("matar")) {
			protagonista.morir();
		}
		else if(trigger.getAfter_trigger().equals("lastimar")) {
			protagonista.lastimar();	// donde el metodo lastimar le quita X cantidad de vida
		}
	}

	public String conjugarNpc() {
		String articulo = "";
		if(this.nombre != "borrado") {
			if (this.genero == Genero.FEMALE) {
				articulo = this.numero == Numero.SINGULAR ? " una" : "";
			} else {
				articulo = this.numero == Numero.SINGULAR ? " un" : "";
			}
			return "Hay" + articulo + " " + this.nombre + ".";
		}
		else
			return "";
	}

}
