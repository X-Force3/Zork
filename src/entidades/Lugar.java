package entidades;

import java.util.List;

public class Lugar {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private String segundoNombre;// Juani: Este atributo s�lo lo tienen los lugares que act�an como obst�culos.
	private List<Item> items;
	private List<Trigger> triggers;
	private String descripcion;

	public Lugar() {
		this.nombre = " ";
	}
	
	public Lugar(String nombre, Genero genero, Numero numero, String descripcion) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.descripcion = descripcion;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}

	public String describirObjetosDisponibles() {
		String queHay = "";
		if (this.triggers != null && this.descripcion != null) {
			return "Hay " + this.conjugar() + ".";
		}

		if (items == null || items.isEmpty())
			queHay = "no hay nada";
		else {
			if (items.size() == 1) {
				queHay = "hay " + items.get(0).conjugarItem();
			} else {
				for (int i = 0; i < items.size() - 2; i++) {
					queHay = queHay + items.get(i).conjugarItem() + ", ";
				}
				queHay = "hay " + queHay + items.get(items.size() - 2).conjugarItem() + " y "
						+ items.get(items.size() - 1).conjugarItem();
			}
		}
		return "En " + this.conjugar() + " " + queHay + ".";
	}

	public String conjugar() {
		String conjugacion = "";
		if (this.triggers != null && this.descripcion != null) {
			if (this.genero == Genero.FEMALE) {
				conjugacion = this.numero == Numero.SINGULAR ? "una" : "unas";
			} else {
				conjugacion = this.numero == Numero.SINGULAR ? "un" : "unos";
			}
		} else {
			if (this.genero == Genero.FEMALE) {
				conjugacion = this.numero == Numero.SINGULAR ? "la" : "las";
			} else {
				conjugacion = this.numero == Numero.SINGULAR ? "el" : "los";
			}
		}
		return conjugacion + " " + this.nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public List<Item> getItems() {
		return items;
	}

	public void eliminarTrigger(Trigger trigger) {
		// codigo

	}

	public void agregarTrigger(Trigger trigger) {
		// codigo

	}

	public void ejecutarTrigger(Trigger trigger) {
		// el trigger podria cambiar el nombre del lugar talvez. asi, cuando el jugador
		// quiera cambiar de ubicacion,
		// el metodo de buscar obstaculo no lo encuentre y lo deje
		if (trigger.getAfter_trigger().equals("remove"))
			this.nombre = "borrado";
		if (trigger.getAfter_trigger().equals("cambiar nombre")) // ver lo del posible atributo de trigger "descripcion"
			this.nombre = this.segundoNombre;// Juani: Reemplac� la l�nea que le asignaba "" por esta que le asigna el
												// segundo nombre...
//		O sea, el nombre del lugar luego de ejecutar el Trigger.

	}

	public String verificarTrigger(Item item) {
		for (Trigger elemento : this.triggers) {
			if (elemento.getType().equals("item") && (item.getNombre().equals(elemento.getThing()))) {
				this.ejecutarTrigger(elemento);
				return elemento.getOn_trigger();
			}
		}
		return "Eso no ha dado ningún resultado...";
	}

	public void eliminarItemLugar(Item item) {
		if (this.items != null && this.items.contains(item))
			this.items.remove(item);
	}

	@Override
	public String toString() {
		return "Lugar [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", segundoNombre="
				+ segundoNombre + ", items=" + items + ", triggers=" + triggers + ", descripcion=" + descripcion + "]";
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Genero getGenero() {
		return genero;
	}

	public Numero getNumero() {
		return numero;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public List<Trigger> getTriggers() {
		return triggers;
	}

}
