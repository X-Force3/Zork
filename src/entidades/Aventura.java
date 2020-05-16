package entidades;

import java.util.List;

public class Aventura {
	
	private String bienvenida;
	private String locacionInicial;
	private String titulo;
	private List<String> locaciones;
	private List<Endgame> endgame;

	

	public Aventura(String bienvenida, String locacionInicial, String titulo, List<String> locaciones,
			List<Endgame> endgame) {
		super();
		this.bienvenida = bienvenida;
		this.locacionInicial = locacionInicial;
		this.titulo = titulo;
		this.locaciones = locaciones;
		this.endgame = endgame;
	}

	public void ejecutarAventura() {
		
	}

	public String getBienvenida() {
		return bienvenida;
	}

	public String getLocacionInicial() {
		return locacionInicial;
	}

	public List<String> getLocaciones() {
		return locaciones;
	}

}
