package componentes;

import java.awt.image.BufferedImage;

public class ImagenUbicacion {
	
	static final int ANCHO = 55;//JuegoJFrame.WIDTH_GAME / 5 ;
	
	BufferedImage img;
	String nombre;
	int x;
	int y;
	
	public ImagenUbicacion( String nombre,BufferedImage img) {
		this.img = img;
		this.nombre = nombre;
		this.y = JuegoJFrame.HEIGHT_GAME * 4/5 - img.getHeight();
	}

	@Override
	public String toString() {
		return "IU [n=" + nombre + ", x=" + x + ", y=" + y + "]";
	}
	
}
