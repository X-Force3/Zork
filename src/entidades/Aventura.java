package entidades;

import java.util.List;

public class Aventura {
	
	private String bienvenida;
	private Locacion locacionInicial;
	private List<Locacion> locaciones;
	
	public Aventura(String welcome, Locacion locacionInicial, List<Locacion> locaciones) {
		super();
		this.bienvenida = welcome;
		this.locacionInicial = locacionInicial;
		this.locaciones = locaciones;
	}

	public void ejecutarAventura() {
		
	}

	public String getBienvenida() {
		return bienvenida;
	}

	public Locacion getLocacionInicial() {
		return locacionInicial;
	}

	public List<Locacion> getLocaciones() {
		return locaciones;
	}

}
