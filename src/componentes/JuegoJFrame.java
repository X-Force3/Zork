package componentes;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import entidades.Item;
import entidades.Lugar;
import entidades.Npc;
import entidades.Ubicacion;

public class JuegoJFrame extends JFrame implements Runnable{
	
	//link: https://www.youtube.com/watch?v=2A7pjyXtXr8

	private static final long serialVersionUID = -8659850278449602685L;
	
	public static final String PATH_RESOURCES = "recursos/";
	public static final String PATH_SPRITES = PATH_RESOURCES +"sprites/";

	public static final int WIDTH_WINDOW = 500;
	public static final int HEIGHT_WINDOW = 600;
	
	public static final int WIDTH_GAME = WIDTH_WINDOW;
	public static final int HEIGHT_HEADER = HEIGHT_WINDOW * 3/10;//450;
	public static final int HEIGHT_GAME = HEIGHT_WINDOW * 5/10;//450;
	public static final int HEIGHT_INPUT = HEIGHT_WINDOW * 2/10;

	private Thread hilo;
	private TextoJPanel textoPanel;
	private LugarJPanel juegoPanel;
	private InputJPanel inputPanel;

	public JuegoJFrame(InputTextListener inputTextListener, String titulo) {
		setTitle(titulo);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PATH_RESOURCES + "logo.jpg"));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));//layout que me permite agregar componentes linealmente, Y_AXIS = modo vertical
		
		
		juegoPanel = new LugarJPanel(WIDTH_WINDOW, HEIGHT_GAME);
		juegoPanel.bg = Toolkit.getDefaultToolkit().getImage(PATH_SPRITES + "background.png");
		add(juegoPanel);
		
		hilo = new Thread();
		hilo.start();

		textoPanel = new TextoJPanel(WIDTH_WINDOW,HEIGHT_HEADER);
		add(textoPanel);
		
		inputPanel = new InputJPanel(WIDTH_WINDOW,HEIGHT_INPUT, inputTextListener);
		add(inputPanel);
		
		pack();
		setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setText(String text) {
		textoPanel.updateText( "> " + text);//"> Estás en jardín de la casa. Se puede observar una ventana y otra puerta totalmente blindadas y cerradas. En el techo se llega a ver una linda chimenea por la que puedes acceder al living de la casa.";
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		Toolkit tool = Toolkit.getDefaultToolkit();
		juegoPanel.bg = tool.getImage(PATH_SPRITES + ubicacion.getNombre() + ".png");
		
		juegoPanel.agregarImagenes(ubicacion.getItems(), ubicacion.getNpcs());
	}

	public void eliminarItem(String nombre) {
		juegoPanel.eliminarItem(nombre);
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
	
}
