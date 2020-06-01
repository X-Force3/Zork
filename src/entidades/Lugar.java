package entidades;

import java.util.List;

public class Lugar {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<Item> items;
	private List<Trigger> triggers;

	public Lugar(String nombre, Genero genero, Numero numero, List<Item> items, List<Trigger> triggers) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.items = items;
		this.triggers = triggers;
	}

	public String describirObjetosDisponibles() {
		String queHay = "";
		if (items.isEmpty())
			queHay = "no hay nada. Prueba en otro lugar";
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

	private String conjugar() {
		String conjugacion = "";
		if (this.genero == Genero.FEMALE) {
			conjugacion = this.numero == Numero.SINGULAR ? "la" : "las";
		} else {
			conjugacion = this.numero == Numero.SINGULAR ? "el" : "los";
		}
		return conjugacion + " " + this.nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<Item> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Lugar [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", items=" + items + "]";
	}
	
	public void eliminarTrigger(Trigger trigger) {
		// codigo
		
	}
	
	public void agregarTrigger(Trigger trigger) {
		// codigo
		
		
	}
	
	public void ejecutarTrigger(Item item) {
		// el trigger podria cambiar el nombre del lugar talvez. asi, cuando el jugador quiera cambiar de ubicacion,
		// el metodo de buscar obstaculo no lo encuentre y lo deje
		
		
		
	}
	
	public void verificarTrigger(Item item) {
		
		
		
	}
	
	

}
