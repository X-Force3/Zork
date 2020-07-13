package componentes;

import java.awt.Toolkit;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class JuegoJFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = -8659850278449602685L;
	
	public static final String PATH_RESOURCES = "recursos/";
	public static final String PATH_SPRITES = PATH_RESOURCES +"sprites/";

	public static final int WIDTH_WINDOW = 600;
	public static final int HEIGHT_WINDOW = 600;
	
	public static final int WIDTH_GAME = WIDTH_WINDOW;
	public static final int HEIGHT_GAME = HEIGHT_WINDOW * 3/4;//450;
	public static final int HEIGHT_INPUT = HEIGHT_WINDOW * 1/4;

	private Thread hilo;
	private LugarJPanel juegoPanel;
	private InputJPanel inputPanel;

	public JuegoJFrame() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		juegoPanel = new LugarJPanel(WIDTH_WINDOW, HEIGHT_GAME);
		juegoPanel.bg = Toolkit.getDefaultToolkit().getImage(PATH_SPRITES + "background.png");
		juegoPanel.items = Arrays.asList(
				Toolkit.getDefaultToolkit().getImage(PATH_SPRITES + "piedra.png"),
				Toolkit.getDefaultToolkit().getImage(PATH_SPRITES + "cartel.png")
		);
		add(juegoPanel);
		hilo = new Thread();
		hilo.start();

		inputPanel = new InputJPanel(WIDTH_WINDOW,HEIGHT_INPUT);
		add(inputPanel);
		
		pack();
		setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setText(String text) {
		juegoPanel.text = "> " + text;//"> Estás en jardín de la casa. Se puede observar una ventana y otra puerta totalmente blindadas y cerradas. En el techo se llega a ver una linda chimenea por la que puedes acceder al living de la casa.";
	}

	@Override
	public void run() {
		while (true) {
			try {
				juegoPanel.actualizar();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new JuegoJFrame().run();
	}
	
}
