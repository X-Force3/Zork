package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entidades.Item;
import entidades.Npc;
import entidades.Protagonista;
import entidades.Ubicacion;

public class LugarJPanel extends JPanel {

	private static final long serialVersionUID = -6383859922990600789L;
	private int ancho;
	private int alto;

	private Sprite personajeSprite;
	
	int vida = 0;
	private static final int ANCHO_BARRA_VIDA = 120;

	BufferedImage bg;
	List<ImagenUbicacion> imagenes = new ArrayList<ImagenUbicacion>();

	public LugarJPanel(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		personajeSprite = new Sprite(850, 639, 4, 3, JuegoJFrame.PATH_SPRITES + "character1.png");
		personajeSprite.setCantTotal(9);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		// dibuja la ubicacion
		if (bg != null) {
			g2d.drawImage(bg, 0, 0, ancho, alto, this);
		} else {
			g2d.setColor(vida > 0? Color.WHITE : Color.BLACK);
			g2d.fillRect(0, 0, ancho, alto);
		}

		if (endgameMensaje == null) {
			
			// dibujo la barra de vida
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Dialog", Font.BOLD, 12));
			g2d.drawString("VIDA", 20, 25);
			g2d.setColor(Color.GREEN);
			g2d.drawRect(55, 15, ANCHO_BARRA_VIDA, 15);
			g2d.fillRect(55, 15,  (int)(ANCHO_BARRA_VIDA *((float)vida / Protagonista.VIDA_INICIAL)), 15);
			
			// dibuja items y npcs
			for(int i=0; i < imagenes.size(); i++) {
				g2d.drawImage(imagenes.get(i).img, imagenes.get(i).x, imagenes.get(i).y, this);
			}

			// dibuja el personaje
			int mx = personajeSprite.getMX();
			int my = personajeSprite.getMY();
			int positionChX = 30;
			int positionChY = alto * 2 / 5;
			g2d.drawImage(personajeSprite.getImagen(), positionChX, positionChY,
					positionChX + personajeSprite.ancho / 2, positionChY + personajeSprite.alto / 2, mx, my,
					mx + personajeSprite.ancho, my + personajeSprite.alto, this);
		} else {
			//endgame
			g2d.setColor(g2d.getColor() == Color.BLACK?Color.WHITE:Color.BLACK);
			g2d.setFont(new Font("Dialog", Font.BOLD, 24));
			g2d.drawString(String.format("%15s", endgameMensaje), ancho*1/3, alto*4/6);
		}
	}

	/*
	 * Actualiza la siguiente cuadricula del sprite (personaje) y redibuja de nuevo
	 * inclutendo los npcs y items.
	 */
	public void actualizar() {
		personajeSprite.actualizar();
		repaint();
	}

	/*
	 * @param ubicacion actual del personaje Guardo la imagen de la ubicacion en bg
	 * (background).
	 */
	public void setUbicacion(Ubicacion ubicacion) {
		try {
			File fileBg = new File(JuegoJFrame.PATH_SPRITES + ubicacion.getNombre() + ".png");
			if (fileBg.exists())
				bg = ImageIO.read(fileBg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		agregarImagenes(ubicacion.getItems(), ubicacion.getNpcs());
	}

	/*
	 * Guardo las imagenes de los items y npcs de la ubicación seteada.
	 */
	void agregarImagenes(List<Item> items, List<Npc> npcs) {
		imagenes = new ArrayList<>();
		for (Npc npc : npcs) {
			BufferedImage img;
			try {
				img = ImageIO.read(new File(JuegoJFrame.PATH_SPRITES + npc.getNombre() + ".png"));
				imagenes.add(new ImagenUbicacion(npc.getNombre(), img));
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		for (Item item : items) {
			BufferedImage img;
			try {
				img = ImageIO.read(new File(JuegoJFrame.PATH_SPRITES + item.getNombre() + ".png"));
				imagenes.add(new ImagenUbicacion(item.getNombre(), img));
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		definirPosicionesImagenes();
	}

	/*
	 * Asigno para cada imagen una posicion donde se va a dibujar en el panel. Nota:
	 * la posicion en Y es la misma para todos, ver ImagenUbicacion class
	 */
	private void definirPosicionesImagenes() {
		if (imagenes.size() == 0)
			return;

		int puntero = ancho - 10; // comienzo en el extremo derecho del panel con un pequenio espacio para que no
									// quede pegado al costado
		for (int i = 0; i < imagenes.size(); i++) {
			int decremento = imagenes.get(i).img.getWidth(); // el decremento es el ancho que la imagen ocupará
			imagenes.get(i).x = puntero - decremento; // asigno la coordenada en x desde donde se quedó el puntero menos
														// el decremento
			puntero -= (decremento + 7); // actualizo el puntero que se va acercando más hacia la izquierda
		}
	}

	/*
	 * Para actualizar me fijo si ese npc o item tiene una segunda imagen. La
	 * segunda imagen debe seguir la siguiente nomenclatura: nombre1.png En caso de
	 * no encontrase elimina la imagen.
	 * 
	 * @param nombre del npc o item
	 */
	public void actualizarImagenUbicacion(String nombre) {
		boolean encontrado = false;
		int index = 0;
		while (!encontrado && index < imagenes.size()) {
			if (imagenes.get(index).nombre.equals(nombre)) {
				encontrado = true;
				File file = new File(JuegoJFrame.PATH_SPRITES + imagenes.get(index).nombre + "1.png");
				if (file.exists()) {
					BufferedImage img1;
					try {
						img1 = ImageIO.read(file);
						imagenes.get(index).img = img1;
					} catch (IOException e) {
						// e.printStackTrace();
						imagenes.remove(index);
					}
				} else {
					imagenes.remove(index);
				}
			}
			index++;
		}
	}

	private String endgameMensaje;
	
	public void finalizar(String endgameMensaje) {
        this.endgameMensaje = endgameMensaje;
		File file = new File(JuegoJFrame.PATH_SPRITES + "endgame.jpg");
		if (file.exists()) {
			try {
				bg = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void mostrarFinDeJuego() {
		bg = null;
		endgameMensaje = "¡Perdiste!";
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(ancho, alto);
	}

}
