package entidades;

import java.util.List;

public class Configuracion {

	private String bienvenida;
	private String titulo;
	private List<Endgame> endgame;
	//la ubicacion inicia es el primero de la lista

	public Configuracion(String bienvenida,String titulo, List<Endgame> endgame) {
		super();
		this.bienvenida = bienvenida;
		this.titulo = titulo;
		this.endgame = endgame;
	}

	public void ejecutarAventura() {

	}

	public String getBienvenida() {
		return bienvenida;
	}

}
