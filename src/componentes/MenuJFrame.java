package componentes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entidades.Aventura;

public class MenuJFrame extends JFrame {

	private static final long serialVersionUID = 296339043406758110L;
	private static final String RUTA = "recursos/";
	public static final String AVENTURA_1 = RUTA + "aventuraentrega.json";
	public static final String AVENTURA_PROFE = RUTA + "aventuraProfe.json";

	private JPanel contentPaneMenu;

	private JTextField txtNameField;
	private JComboBox<String> comboBox;
	private JLabel lblAventuraLabel;
	private JButton btnBoton;
	private JLabel lblNameLabel;

	public MenuJFrame() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 217);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPaneMenu = new JPanel();
		setContentPane(contentPaneMenu);
		contentPaneMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneMenu.setLayout(null);

		init();

	}

	private void init() {
		comboBox = new JComboBox<String>();

		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setFont(new Font("Century", Font.PLAIN, 12));
		comboBox.setBounds(44, 51, 256, 33);
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Aventura 1: La Isla Peligrosa ", "Aventura 2: Ejemplo del Profe" }));

		contentPaneMenu.add(comboBox);

		lblAventuraLabel = new JLabel("Seleccione la Aventura a Ejecutar:\r\n");
		lblAventuraLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAventuraLabel.setBounds(34, 25, 244, 14);
		contentPaneMenu.add(lblAventuraLabel);

		btnBoton = new JButton("Jugar !");
		btnBoton.setEnabled(false);

		btnBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreJugador = txtNameField.getText();
				Aventura aventura;
				if (comboBox.getSelectedIndex() == 0) {
					aventura = new Aventura(AVENTURA_1, nombreJugador);
				} else {
					aventura = new Aventura(AVENTURA_PROFE, nombreJugador);
				}
				desaparecer();
				aventura.comenzar();

			}
		});

		btnBoton.setBounds(285, 145, 114, 23);
		contentPaneMenu.add(btnBoton);

		lblNameLabel = new JLabel("Escriba su Nombre:");
		lblNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNameLabel.setBounds(31, 95, 182, 14);
		contentPaneMenu.add(lblNameLabel);

		txtNameField = new JTextField();
		txtNameField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNameField.setText("");

		txtNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!txtNameField.getText().equals("")) {
					btnBoton.setEnabled(true);
				}
			}
		});
		txtNameField.setBounds(41, 118, 172, 20);
		txtNameField.setColumns(10);
		contentPaneMenu.add(txtNameField);
	}

	private void desaparecer() {
		// this.setVisible(false);
		this.dispose();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJFrame frame = new MenuJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
