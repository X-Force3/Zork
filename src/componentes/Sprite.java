package componentes;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	int ancho;
	int alto;
	
	private int cantHorizontal;
	private int cantVertical;
	private int posicion;
	
	int cantTotal;
	
	private Image imagen;

	public Sprite(int anchoTotalPx, int altoTotalPx, int cantHorizontal, int cantVertical, String spriteImagen) {
		this.ancho = anchoTotalPx / cantHorizontal;
		this.alto = altoTotalPx / cantVertical;
		this.cantHorizontal = cantHorizontal;
		this.cantVertical = cantVertical;
		try {
			imagen = ImageIO.read(new File(spriteImagen));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.cantTotal = cantHorizontal * cantVertical;
		this.posicion = 0;
	}

	public Sprite(int ancho, int alto, String spriteImagen) {
		this.ancho = ancho;
		this.alto = alto;
		this.cantHorizontal = 1;
		this.cantVertical = 1;
		this.cantTotal = 1;
		imagen = Toolkit.getDefaultToolkit().getImage(spriteImagen);
		//bi = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Actualiza la cuadrícula siguiente a dibujar
	 * */
	public void actualizar() {
		posicion++;
		if (posicion > cantTotal)
			posicion = 0;
	}

	public void setCantTotal(int cantTotal) {
		this.cantTotal = cantTotal;
	}
	
	public int getMX() {
		return (posicion % cantHorizontal)*ancho;
	}

	public int getMY() {
		return (posicion / (cantVertical+1))*alto;
	}

	public Image getImagen() {
		return imagen;
	}
	
	
}
