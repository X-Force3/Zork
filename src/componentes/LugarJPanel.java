package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class LugarJPanel extends JPanel {
	
	private static final long serialVersionUID = -6383859922990600789L;
	private int ancho;
	private int alto;

	Font customFont;
	String text = "";
	
	private Sprite character;

	int posicionCharacter = 0;
	
	Image bg;
	List<Image> items = new ArrayList<Image>();

	public LugarJPanel(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		customFont = null;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File(JuegoJFrame.PATH_RESOURCES + "dogicapixel.ttf")).deriveFont(8f);
		} catch (FontFormatException | IOException e) {
			customFont = new Font("TimesRoman", Font.PLAIN, 20);
			e.printStackTrace();
		}

		character = new Sprite(850, 639, 4, 3, JuegoJFrame.PATH_SPRITES + "character1.png");
		character.setCantTotal(9);
	}


	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		g2d.drawImage(bg, 0, 0, ancho, alto, this);

		int mx = character.getMX(posicionCharacter);
		int my = character.getMY(posicionCharacter);
		int positionChX = ancho / 2 - character.ancho / 2;
		int positionChY = alto*7/13;
		g2d.drawImage(character.getImagen(), positionChX, positionChY, positionChX + character.ancho/2, positionChY + character.alto/2, mx,
				my, mx + character.ancho, my + character.alto, this);

		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(10, 10, ancho -25, 130, 20, 30);

		g2d.setColor(Color.BLACK);
		g2d.setFont(customFont);
		drawStringMultiLine(g2d, text, ancho - 40, 20, 30);
		
		//add items
		int anchoItem = 50;
		for(int i=0; i<items.size(); i++) {
			g2d.drawImage(items.get(i), ancho/(items.size()+2)*(i), alto*2/3, anchoItem, anchoItem, this);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(ancho, alto);
	}

	/*
	 * Dibuja el texto en el panel calculando que 
	 * no se pase de largo en una sola l�nea.
	 * @param lineWidth : ancho m�ximo de una l�nea
	 * */
	private static int drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
		FontMetrics m = g.getFontMetrics();
		if (m.stringWidth(text) < lineWidth) {
			g.drawString(text, x, y);
		} else {
			String[] words = text.split(" ");
			String currentLine = words[0];
			for (int i = 1; i < words.length; i++) {
				if (m.stringWidth(currentLine + words[i]) < lineWidth) {
					currentLine += " " + words[i];
				} else {
					g.drawString(currentLine, x, y);
					y += m.getHeight() + 10;// +10 added
					currentLine = words[i];
				}
			}
			if (currentLine.trim().length() > 0) {
				g.drawString(currentLine, x, y);
			}
		}
		return  m.getHeight() + 10;
	}

	public void actualizar() {
		posicionCharacter++;
		if(posicionCharacter > character.cantTotal)
			posicionCharacter = 0;
		repaint();
	}
	
}