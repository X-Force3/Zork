package componentes;

import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import entidades.Ubicacion;

public class JuegoJFrame extends JFrame implements Runnable {

	// link: https://www.youtube.com/watch?v=2A7pjyXtXr8

	private static final long serialVersionUID = -8659850278449602685L;

	public static final String PATH_RESOURCES = "recursos/";
	public static final String PATH_SPRITES = PATH_RESOURCES + "sprites/";

	public static final int WIDTH_WINDOW = 550;
	public static final int HEIGHT_WINDOW = 600;

	public static final int WIDTH_GAME = WIDTH_WINDOW;
	public static final int HEIGHT_HEADER = HEIGHT_WINDOW * 3 / 10;
	public static final int HEIGHT_GAME = HEIGHT_WINDOW * 5 / 10;
	public static final int HEIGHT_INPUT = HEIGHT_WINDOW * 2 / 10;
	
	public static final int TIME_TO_UPDATE = 200;

	private Thread hilo;
	private TextoJPanel textoPanel;
	private LugarJPanel juegoPanel;
	private InputJPanel inputPanel;
	
	private boolean finDeJuego = false;

	public JuegoJFrame(InputTextListener inputTextListener, String titulo) {
		setTitle(titulo);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PATH_RESOURCES + "logo.jpg"));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));// layout que me permite agregar componentes
																		// linealmente, Y_AXIS = modo vertical

		juegoPanel = new LugarJPanel(WIDTH_WINDOW, HEIGHT_GAME);
		add(juegoPanel);

		hilo = new Thread();
		hilo.start();

		textoPanel = new TextoJPanel(WIDTH_WINDOW, HEIGHT_HEADER);
		add(textoPanel);

		inputPanel = new InputJPanel(WIDTH_WINDOW, HEIGHT_INPUT, inputTextListener);
		add(inputPanel);

		setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	@Override
	public void run() {
		while (!finDeJuego) {
			try {
				juegoPanel.actualizar();
				Thread.sleep(TIME_TO_UPDATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setVida(int vidaActual) {
		juegoPanel.vida = vidaActual;
		if(vidaActual <= 0) {
			finDeJuego = true;
			juegoPanel.mostrarFinDeJuego();
			inputPanel.deshabilitar();
		}
	}

	public void setText(String text) {
		textoPanel.updateText("> " + text);// "> Estás en jardín de la casa.";
	}

	public void setUbicacion(Ubicacion ubicacion) {
		juegoPanel.setUbicacion(ubicacion);
	}

	public void actualizarItem(String nombre) {
		juegoPanel.actualizarImagenUbicacion(nombre);
	}

	public void actualizarNpc(String nombre) {
		juegoPanel.actualizarImagenUbicacion(nombre);
	}
	
	public void finalizar(String nombre) {
		juegoPanel.finalizar(nombre);
		finDeJuego = true;
		juegoPanel.repaint();
		inputPanel.deshabilitar();
	}

}
