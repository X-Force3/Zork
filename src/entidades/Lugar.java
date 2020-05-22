package entidades;

import java.util.List;

public class Lugar {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<Item> items;
	
	public Lugar(String nombre, Genero genero, Numero numero, List<Item> items) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.items = items;
	}
	
	public String describirObjetosDisponibles() {
		String queHay = "";
		if(items.isEmpty())
			queHay = "no hay nada. Prueba en otro lugar";
		else {
			if (items.size() == 1) {
				queHay = "hay " + conjugarItem(this.items.get(0));
			}else {
				for(int i = 0; i < items.size() - 2; i++) {
					Item item = this.getItems().get(i);
					queHay = queHay + conjugarItem(item) + ", ";
				}
				Item anteultimoItem = this.items.get(items.size()-2);
				Item ultimoItem = this.items.get(items.size()-1);
				queHay = "hay " + queHay + conjugarItem(anteultimoItem) + " y " + conjugarItem(ultimoItem); 
			}
		}
		return "En " + conjugar(genero, numero) + " " + queHay + ".";
	}
	
	//Luciano: Agrego constructor 
	public Lugar(String nombre, Genero genero, Numero numero, List<String> items) {
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
		this.items = items;
	}
	
	//Luciano: Agrego gete nombre
	public String getNombre() {
		return this.nombre;
	}
	
	

	private String conjugar(Genero genero, Numero numero) {
		String conjugacion= "";
		if(this.genero == Genero.FEMALE) {
			conjugacion = this.numero == Numero.SINGULAR? "la" : "las";
		}else {
			conjugacion = this.numero == Numero.SINGULAR? "el" : "los";
		}
		return conjugacion + " " + this.nombre;
	}
	
	private String conjugarItem(Item item) {
		String conjugacion= "";
		if(item.getGenero() == Genero.FEMALE) {
			conjugacion = item.getNumero() == Numero.SINGULAR? "una " : "";
		}else {
			conjugacion = item.getNumero() == Numero.SINGULAR? "un " : "";
		}
		return conjugacion + item.getNombre();
	}

	public List<Item> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Lugar [nombre=" + nombre + ", genero=" + genero + ", numero=" + numero + ", items=" + items + "]";
	}
	
}
