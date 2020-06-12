package entidades;

import java.util.List;

public class Configuracion {

	private String bienvenida;
	private String titulo;
	private List<Endgame> endgame;

	public Configuracion(String bienvenida,String titulo, List<Endgame> endgame) {
		super();
		this.bienvenida = bienvenida;
		this.titulo = titulo;
		this.endgame = endgame;
	}

	public String getBienvenida() {
		return bienvenida;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Endgame> getEndgame() {
		return endgame;
	}

}
