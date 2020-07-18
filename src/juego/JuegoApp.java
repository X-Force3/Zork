package juego;

import entidades.Aventura;

public class JuegoApp {

	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.desplegarMenuOpciones();
		menu.solicitarNombreDeJugador();

		if (menu.getPathAventuraElegida() != null) {
			Aventura aventura = new Aventura(menu.getPathAventuraElegida(), menu.getNombreJugador());
			aventura.comenzar();
		}
	}

}
