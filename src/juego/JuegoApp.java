package juego;

import java.awt.event.WindowEvent;

import componentes.CompletarDatos;
import componentes.MenuJFrame;
import entidades.Aventura;

public class JuegoApp {

	public static void main(String[] args) {

		/*
		 * Menu menu = new Menu(); menu.desplegarMenuOpciones();
		 * menu.solicitarNombreDeJugador();
		 * 
		 * if (menu.getPathAventuraElegida() != null) { Aventura aventura = new
		 * Aventura(menu.getPathAventuraElegida(), menu.getNombreJugador());
		 * aventura.comenzar(); }
		 */

		// borrar luego
//		Aventura a = new Aventura("recursos/aventuraProfe.json", "Pepe");
//		a.comenzar();
		new MenuJFrame(new CompletarDatos() {

			@Override
			public void datosCompletados(String pathAventura, String nombreJugador) {
				new Aventura(pathAventura,nombreJugador).comenzar();
			}
			
		});
	}

}
