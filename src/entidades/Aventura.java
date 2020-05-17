package entidades;

import java.util.List;

public class Aventura {
	
	private Configuracion configuracion;
	private List<Ubicacion> ubicaciones;
	private List<Item> items;
	
	public Aventura(Configuracion configuracion, List<Ubicacion> ubicaciones, List<Item> items) {
		super();
		this.configuracion = configuracion;
		this.ubicaciones = ubicaciones;
		this.items = items;
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public List<Item> getItems() {
		return items;
	}

	public void comenzar() {

	}
}
