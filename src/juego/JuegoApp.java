package juego;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import componentes.MenuJFrame;
import entidades.Aventura;

public class JuegoApp {
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MenuJFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public static void comenzarAventura(String pathAventura, String nombreJugador) {
		new Timer().schedule( 
		        new TimerTask() {
		            @Override
		            public void run() {
		            	new Aventura(pathAventura, nombreJugador).comenzar();
		            }
		        }, 
		        2000
		);
	}

}
