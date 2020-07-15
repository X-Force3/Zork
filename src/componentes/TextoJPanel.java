package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TextoJPanel extends JPanel{
	
	private static final long serialVersionUID = 1757583909909872876L;
	JTextPane label;
	JPanel containerPanel;
	JScrollPane panelPane;
	Font customFont;
	
	int ancho;
	int alto;
	
	public TextoJPanel(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		setLayout(new BorderLayout(5,5));
		label = new JTextPane();
		label.setEditable(false);
		label.setBorder(new EmptyBorder(5,10,10,10));
		
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setLineSpacing(set, 0.5f);
		label.setParagraphAttributes(set, true);
		
		//label.setLineWrap(true);
		//label.setWrapStyleWord(true);
		label.setFocusable(false);
		label.setSize(ancho, alto);
		label.setPreferredSize(new Dimension(ancho,alto));
		
		customFont = null;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File(JuegoJFrame.PATH_RESOURCES + "learners.ttf")).deriveFont(22f);
		} catch (FontFormatException | IOException e) {
			customFont = new Font("TimesRoman", Font.PLAIN, 24);
			e.printStackTrace();
		}
		
		//http://javatechniques.com/blog/setting-jtextpane-font-and-color/
		 MutableAttributeSet attrs = label.getInputAttributes();
	        StyleConstants.setFontFamily(attrs, customFont.getFamily());
	        StyleConstants.setFontSize(attrs, customFont.getSize());
			
			label.setFont(customFont);
		
		
		
		panelPane = new JScrollPane(label);
		panelPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(panelPane,BorderLayout.CENTER);

        this.setOpaque(true);
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(ancho,alto));
		setSize(ancho, alto);
	}
	
	public void updateText(String text) {
		label.setText(text);
	}
	
}
