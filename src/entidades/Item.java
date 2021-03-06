package entidades;

import java.util.List;

public class Item {

	private String nombre;
	private Genero genero;
	private Numero numero;
	private List<String> acciones;
	private List<String> efectosSobre;
	private String descripcion;

	public Item() {
		
		this.nombre = " ";
	}
	
	public String getDescricpion() {
		return descripcion;
	}

	public void setDescricpion(String descricpion) {
		this.descripcion = descricpion;
	}

	public Item(String nombre, Genero genero, Numero numero) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.numero = numero;
	}

	public void setAcciones(List<String> acciones) {
		this.acciones = acciones;
	}

	public void setEfectosSobre(List<String> efectosSobre) {
		this.efectosSobre = efectosSobre;
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

	public List<String> getAcciones() {
		return acciones;
	}

	public List<String> getEfectosSobre() {
		return efectosSobre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String conjugarItem() {
		String conjugacion = "";
		if (this.genero == Genero.FEMALE) {
			conjugacion = this.numero == Numero.SINGULAR ? "una " : "";
		} else {
			conjugacion = this.numero == Numero.SINGULAR ? "un " : "";
		}
		return conjugacion + this.nombre;
	}

	@Override
	public String toString() {
		return "Item [nombre=" + nombre + ", gen=" + genero + ", num=" + numero + ", acciones=" + acciones
				+ ", efectosSobre=" + efectosSobre + "]";
	}
	
	public boolean esItemDeInventario() {
		return (this.acciones.contains("agarrar") || this.acciones.contains("tomar") || this.acciones.contains("guardar"));
	}

}
