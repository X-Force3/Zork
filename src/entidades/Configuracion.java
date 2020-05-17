package entidades;

import java.util.List;

public class Configuracion {

	private String bienvenida;
	private String ubicacionInicial;
	private String titulo;
	private List<Endgame> endgame;

	public Configuracion(String bienvenida, String locacionInicial, String titulo, List<Endgame> endgame) {
		super();
		this.bienvenida = bienvenida;
		this.ubicacionInicial = locacionInicial;
		this.titulo = titulo;
		this.endgame = endgame;
	}

	public void ejecutarAventura() {

	}

	public String getBienvenida() {
		return bienvenida;
	}

	public String getLocacionInicial() {
		return ubicacionInicial;
	}

}
