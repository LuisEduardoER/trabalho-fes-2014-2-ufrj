package bt.com.vocealuga.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import br.com.vocealuga.controller.FilialController;
import br.com.vocealuga.controller.GrupoController;
import br.com.vocealuga.controller.ModeloController;
import br.com.vocealuga.controller.ReservaController;
import br.com.vocealuga.hibernate.NomeAtributo;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.util.Util;

@SuppressWarnings("serial")
public class ReservaView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JFormattedTextField formatted_txtDataDevolucao;
	private JFormattedTextField formatted_txtDataLocacao;
	private JTextField txtMotorista1;
	private JTextField txtMotorista2;
	private JTextField txtMotorista3;
	private JTextField txtMotorista4;
	private JTextField txtMotorista5;
	private JComboBox comboBoxModelo;
	private JComboBox comboBoxGrupo;
	private JComboBox comboBoxFilial;

	/**
	 * Create the frame.
	 */
	public ReservaView(final ReservaController reservaController) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDe = new JLabel("Nome da Filial");
		lblDe.setBounds(12, 24, 126, 15);
		contentPane.add(lblDe);

		JLabel lblDataDeNascimento = new JLabel("Devolu\u00E7\u00E3o");
		lblDataDeNascimento.setBounds(12, 115, 155, 15);
		contentPane.add(lblDataDeNascimento);

		JButton btnReserva = new JButton("Reserva");
		this.getRootPane().setDefaultButton(btnReserva);

		btnReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String grupo = (String) comboBoxGrupo.getSelectedItem();
				String modelo = (String) comboBoxModelo.getSelectedItem();
				String nomeFilial = (String) comboBoxFilial.getSelectedItem();
				List<Motorista> motoristas = new ArrayList<Motorista>();

				if (Util.preenchido(txtMotorista1.getText())) {
					Motorista motorista = new Motorista();
					motorista.setCnh(txtMotorista1.getText());
					motoristas.add(motorista);
				}

				if (Util.preenchido(txtMotorista2.getText())) {
					Motorista motorista = new Motorista();
					motorista.setCnh(txtMotorista2.getText());
					motoristas.add(motorista);
				}

				if (Util.preenchido(txtMotorista3.getText())) {
					Motorista motorista = new Motorista();
					motorista.setCnh(txtMotorista3.getText());
					motoristas.add(motorista);
				}

				if (Util.preenchido(txtMotorista4.getText())) {
					Motorista motorista = new Motorista();
					motorista.setCnh(txtMotorista4.getText());
					motoristas.add(motorista);
				}

				if (Util.preenchido(txtMotorista5.getText())) {
					Motorista motorista = new Motorista();
					motorista.setCnh(txtMotorista5.getText());
					motoristas.add(motorista);
				}

				boolean sucesso = reservaController.criarReserva(nomeFilial,
						txtCpf.getText(), formatted_txtDataLocacao.getText(),
						formatted_txtDataDevolucao.getText(), modelo, grupo,
						motoristas);
				if (sucesso) {
					JOptionPane.showMessageDialog(null,
							"Reserva criada com sucesso.");
				} else {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro na criação do reserva.");
				}
			}
		});
		btnReserva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		btnReserva.setBounds(151, 327, 117, 25);
		contentPane.add(btnReserva);

		JLabel lblCnh = new JLabel("CPF");
		lblCnh.setBounds(12, 50, 155, 15);
		contentPane.add(lblCnh);

		txtCpf = new JTextField();
		txtCpf.setText("1234567890123");
		txtCpf.setBounds(186, 52, 232, 19);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNApolice = new JLabel("Modelo do carro");
		lblNApolice.setBounds(12, 145, 126, 15);
		contentPane.add(lblNApolice);

		JLabel lblNewLabel = new JLabel("Loca\u00E7\u00E3o");
		lblNewLabel.setBounds(12, 85, 116, 15);
		contentPane.add(lblNewLabel);

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DateFormatter df = new DateFormatter(format);

		formatted_txtDataDevolucao = new JFormattedTextField(df);
		formatted_txtDataDevolucao.setText("01/01/1990");
		formatted_txtDataDevolucao.setBounds(186, 112, 232, 20);
		contentPane.add(formatted_txtDataDevolucao);

		formatted_txtDataLocacao = new JFormattedTextField(df);
		formatted_txtDataLocacao.setText("01/01/2015");
		formatted_txtDataLocacao.setBounds(186, 82, 232, 20);
		contentPane.add(formatted_txtDataLocacao);

		JLabel lblGrupoDoCarro = new JLabel("Grupo do carro");
		lblGrupoDoCarro.setBounds(12, 171, 126, 14);
		contentPane.add(lblGrupoDoCarro);

		JLabel lblMotoristas = new JLabel("Motoristas (por cnh)");
		lblMotoristas.setBounds(12, 205, 179, 14);
		contentPane.add(lblMotoristas);

		txtMotorista1 = new JTextField();
		txtMotorista1.setBounds(10, 227, 181, 20);
		contentPane.add(txtMotorista1);
		txtMotorista1.setColumns(10);

		txtMotorista2 = new JTextField();
		txtMotorista2.setEnabled(false);
		txtMotorista2.setColumns(10);
		txtMotorista2.setBounds(237, 227, 181, 20);
		contentPane.add(txtMotorista2);

		txtMotorista3 = new JTextField();
		txtMotorista3.setEnabled(false);
		txtMotorista3.setColumns(10);
		txtMotorista3.setBounds(10, 258, 181, 20);
		contentPane.add(txtMotorista3);

		txtMotorista4 = new JTextField();
		txtMotorista4.setEnabled(false);
		txtMotorista4.setColumns(10);
		txtMotorista4.setBounds(237, 258, 181, 20);
		contentPane.add(txtMotorista4);

		txtMotorista5 = new JTextField();
		txtMotorista5.setEnabled(false);
		txtMotorista5.setColumns(10);
		txtMotorista5.setBounds(10, 289, 181, 20);
		contentPane.add(txtMotorista5);

		comboBoxModelo = new JComboBox();
		comboBoxModelo.setBounds(186, 140, 232, 24);
		List<Modelo> modelos = ModeloController.listarModelos();
		comboBoxModelo.addItem("");
		for (Modelo modelo : modelos) {
			comboBoxModelo.addItem(modelo.getNome());
		}
		contentPane.add(comboBoxModelo);

		comboBoxGrupo = new JComboBox();
		comboBoxGrupo.setBounds(186, 166, 232, 24);
		List<Grupo> grupos = GrupoController.listarGrupos();
		comboBoxGrupo.addItem("");
		for (Grupo grupo : grupos) {
			comboBoxGrupo.addItem(grupo.getNome());
		}
		contentPane.add(comboBoxGrupo);

		comboBoxFilial = new JComboBox();
		comboBoxFilial.setBounds(186, 19, 232, 24);
		List<Filial> filiais = FilialController.listarFiliais();
		for (Filial filial : filiais) {
			comboBoxFilial.addItem(filial.getNome());
		}
		contentPane.add(comboBoxFilial);

		comboBoxModelo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Util.preenchido((String) comboBoxModelo.getSelectedItem())) {
					comboBoxGrupo.setSelectedItem(null);
					comboBoxGrupo.setEnabled(false);
				} else {
					comboBoxGrupo.setEnabled(true);
				}

			}
		});

		txtMotorista1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMotorista2.setEnabled(true);
			}
		});

		txtMotorista2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMotorista3.setEnabled(true);
			}
		});

		txtMotorista3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMotorista4.setEnabled(true);
			}
		});

		txtMotorista4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMotorista5.setEnabled(true);
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}
