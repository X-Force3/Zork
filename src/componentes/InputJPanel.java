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
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class InputJPanel extends JPanel {

	private static final long serialVersionUID = -3843632241514733050L;
	private static final String INIT_TEXT = "> ";
	private int ancho;
	private int alto;

	InputTextListener inputTextListener;
	private JTextArea textArea;

	public InputJPanel(int ancho, int alto, InputTextListener inputTextListener) {
		this.ancho = ancho;
		this.alto = alto;
		this.inputTextListener = inputTextListener;

		setLayout(new BorderLayout(15, 15));
		setBorder(new EmptyBorder(10, 10, 10, 10));

		textArea = new JTextArea();
		textArea.setText("> ");
		textArea.setBorder(new EmptyBorder(15, 15, 15, 15));
		textArea.setCaretPosition(textArea.getText().length() - 1);

		// "multilineal": mientras el usuario va escribiendo, si se pasa del ancho de la
		// ventana, sigue en una nueva linea.
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		// En lugar de KeyListener, porque agrega el salto de linea igual
		@SuppressWarnings("serial")
		Action enter = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificar();
			}
		};
		textArea.getActionMap().put("insert-break", enter);

		try {

			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(JuegoJFrame.PATH_RESOURCES + "fafers.ttf"));
			font = font.deriveFont(Font.PLAIN, 22);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);

			textArea.setFont(font);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		textArea.setCaretColor(Color.RED);
		add(textArea, BorderLayout.CENTER);

		JButton btn = new JButton();
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setContentAreaFilled(false);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				notificar();
			}

		});
		Image img = Toolkit.getDefaultToolkit().getImage(JuegoJFrame.PATH_SPRITES + "next" + ".png");
		Image newimg = img.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		btn.setIcon(new ImageIcon(newimg));
		btn.setPreferredSize(new Dimension(50, 50));
		add(btn, BorderLayout.EAST);
		setSize(ancho, alto);
	}

	public void notificar() {
		if (!textArea.getText().isEmpty() && !textArea.getText().equals(INIT_TEXT)) {
			inputTextListener.inputText(textArea.getText());
			textArea.setText(INIT_TEXT);
			textArea.setCaretPosition(textArea.getText().length());
		}
	}

	public void deshabilitar() {
		textArea.setEditable(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(54, 187, 161));
		g2.fillRect(0, 0, ancho, alto);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		InputJPanel panel = new InputJPanel(300, 200, new InputTextListener() {

			@Override
			public void inputText(String newText) {

			}
		});
		frame.add(panel);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
