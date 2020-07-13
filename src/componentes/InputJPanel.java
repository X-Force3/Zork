package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class InputJPanel extends JPanel {
	
	private static final long serialVersionUID = -3843632241514733050L;
	private int ancho;
	private int alto;

	private JTextArea messageText;

	public InputJPanel(int ancho, int alto) {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		this.ancho = ancho;
		this.alto = alto;

		messageText = new JTextArea("> ");
		messageText.setCaretPosition(messageText.getText().length() - 1);
		messageText.setLineWrap(true);
		messageText.setWrapStyleWord(true);

		
		//En lugar de KeyListener, porque agrega el salto de linea igual
		@SuppressWarnings("serial")
		Action enter = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				messageText.setText("> ");
				messageText.setCaretPosition(messageText.getText().length());
			}
		};
		messageText.getActionMap().put("insert-break", enter);

		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(JuegoJFrame.PATH_RESOURCES + "dogicapixel.ttf")).deriveFont(14f);
			messageText.setFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		messageText.setCaretColor(Color.RED);
		add(messageText, BorderLayout.CENTER);
		setSize(ancho, alto);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, ancho, alto);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new InputJPanel(300, 200);
		frame.add(panel);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
