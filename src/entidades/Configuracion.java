package entidades;

import java.util.List;

public class Configuracion {

	private String bienvenida;
	private String titulo;
	private List<Endgame> endgames;

	public Configuracion(String bienvenida,String titulo, List<Endgame> endgames) {
		super();
		this.bienvenida = bienvenida;
		this.titulo = titulo;
		this.endgames = endgames;
	}

	public String getBienvenida() {
		return bienvenida;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Endgame> getEndgames() {
		return endgame;
	}

}
