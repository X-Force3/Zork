package juego;

import entidades.Aventura;

public class JuegoApp {

	public static void main(String[] args) {

		/*Menu menu = new Menu();
		menu.desplegarMenuOpciones();
		menu.solicitarNombreDeJugador();
		
		Aventura aventura = new Aventura(menu.getPathAventuraElegida(), menu.getNombreJugador());*/
		
		Aventura aventura = new Aventura(Menu.AVENTURA_1, "Messi"); //Linea para ejecutar rápido
		aventura.comenzar();
		
	}

}
