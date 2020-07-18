package componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entidades.Aventura;

public class MenuJFrame extends JFrame {

	private static final long serialVersionUID = 296339043406758110L;
	private static final String RUTA = "recursos/";
	public static final String AVENTURA_1 = RUTA + "aventuraentrega.json";
	public static final String AVENTURA_PROFE = RUTA + "aventuraProfe.json";

	private PanelConFondo panel;

	private JTextField txtNameField;
	private JComboBox<String> comboBox;
	private JLabel lblAventuraLabel;
	private JButton btnBoton;
	private JLabel lblNameLabel;
	
	private CompletarDatos completarDatos;

	public MenuJFrame(CompletarDatos completarDatos) {
		this.completarDatos = completarDatos;
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 220);
		setResizable(false);
		setLocationRelativeTo(null);

		panel = new PanelConFondo();
		setContentPane(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		init();
		setVisible(true);

	}

	private void init() {
		comboBox = new JComboBox<String>();

		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setFont(new Font("Century", Font.PLAIN, 12));
		comboBox.setBounds(44, 51, 256, 33);
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Aventura: La Isla Peligrosa ", "Micro-aventura de pruebas" }));

		panel.add(comboBox);

		lblAventuraLabel = new JLabel("Seleccione la Aventura a Ejecutar:\r\n");
		lblAventuraLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAventuraLabel.setForeground(Color.WHITE);
		lblAventuraLabel.setBounds(34, 25, 244, 14);
		panel.add(lblAventuraLabel);

		btnBoton = new JButton("Jugar !");
		btnBoton.setEnabled(false);

		btnBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreJugador = txtNameField.getText();
				String pathAventura = comboBox.getSelectedIndex() == 0? AVENTURA_1 : AVENTURA_PROFE;
				completarDatos.datosCompletados(pathAventura, nombreJugador);
				desaparecer();
			}
		});

		btnBoton.setBounds(285, 145, 114, 23);
		panel.add(btnBoton);

		lblNameLabel = new JLabel("Escriba su Nombre:");
		lblNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNameLabel.setForeground(Color.WHITE);
		lblNameLabel.setBounds(31, 95, 182, 14);
		panel.add(lblNameLabel);

		txtNameField = new JTextField();
		txtNameField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNameField.setText("");

		txtNameField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
						if (!txtNameField.getText().equals("")) {
							btnBoton.setEnabled(true);
						}
				  }
				});
		
		txtNameField.setBounds(41, 118, 172, 20);
		txtNameField.setColumns(10);
		panel.add(txtNameField);
	}

	private void desaparecer() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private class PanelConFondo extends JPanel {

		private static final long serialVersionUID = -4626362023408501339L;
		private Image img;

		@Override
		public void paint(Graphics g) {
			img = new ImageIcon("recursos/menu/bg.jpg").getImage();

			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

			setOpaque(false);

			super.paint(g);
		}
	}
}
