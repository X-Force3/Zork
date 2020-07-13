package componentes;

import java.awt.Image;
import java.awt.Toolkit;

public class Sprite {

	int ancho;
	int alto;
	
	private int cantHorizontal;
	private int cantVertical;
	
	int cantTotal;
	
	private Image imagen;

	public Sprite(int anchoTotalPx, int altoTotalPx, int cantHorizontal, int cantVertical, String spriteImagen) {
		this.ancho = anchoTotalPx / cantHorizontal;
		this.alto = altoTotalPx / cantVertical;
		this.cantHorizontal = cantHorizontal;
		this.cantVertical = cantVertical;
		Toolkit herramienta = Toolkit.getDefaultToolkit();
		imagen = herramienta.getImage(spriteImagen);
		//bi = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		this.cantTotal = cantHorizontal * cantVertical;
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

	public void setCantTotal(int cantTotal) {
		this.cantTotal = cantTotal;
	}
	
	public int getMX(int pos) {
		return (pos % cantHorizontal)*ancho;
	}

	public int getMY(int pos) {
		return (pos / (cantVertical+1))*alto;
	}

	public Image getImagen() {
		return imagen;
	}
	
	
}
