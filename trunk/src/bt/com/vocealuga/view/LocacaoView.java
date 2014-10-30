package bt.com.vocealuga.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import br.com.vocealuga.controller.LocacaoController;
import br.com.vocealuga.controller.ModeloController;
import br.com.vocealuga.controller.ReservaController;
import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.modelo.services.CarroService;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;

@SuppressWarnings("serial")
public class LocacaoView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtReservaCpf;
	private JTextField textCpf;
	private JTextField txtMotorista1;
	private JTextField txtMotorista4;
	private JTextField txtMotorista2;
	private JTextField txtMotorista5;
	private JTextField txtMotorista3;

	/**
	 * Create the frame.
	 */
	public LocacaoView(final LocacaoController locacaoController) {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(22, 105, 446, 374);
		contentPane.add(panel);
		
		JLabel lblDe = new JLabel("Cpf da reserva");
		lblDe.setBounds(22, 49, 135, 15);
		contentPane.add(lblDe);
		
		txtReservaCpf = new JTextField();
		txtReservaCpf.setEnabled(false);
		txtReservaCpf.setBounds(138, 47, 188, 19);
		contentPane.add(txtReservaCpf);
		txtReservaCpf.setColumns(10);
		
		textCpf = new JTextField();
		textCpf.setText("1234567890123");
		textCpf.setColumns(10);
		textCpf.setBounds(186, 52, 232, 19);
		panel.add(textCpf);
		
		
		final JCheckBox chckbxTemReserva = new JCheckBox("Tem reserva");
		chckbxTemReserva.setBounds(22, 18, 129, 23);
		contentPane.add(chckbxTemReserva);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		final DateFormatter df = new DateFormatter(format);
		
		final JFormattedTextField formatted_txtDataDevolucao = new JFormattedTextField(df);
		formatted_txtDataDevolucao.setText("01/01/1990");
		formatted_txtDataDevolucao.setBounds(186, 112, 232, 20);
		panel.add(formatted_txtDataDevolucao);
		
		final JFormattedTextField formatted_txtDataLocacao = new JFormattedTextField(df);
		formatted_txtDataLocacao.setEditable(false);
		formatted_txtDataLocacao.setText( UtilData.formata( UtilData.retornaDataSemHora(UtilData.agora()),"dd/MM/YYYY") );
		formatted_txtDataLocacao.setBounds(186, 82, 232, 20);
		panel.add(formatted_txtDataLocacao);
		
		JButton btnSelecionar = new JButton("Selecionar");
		this.getRootPane().setDefaultButton(btnSelecionar);
		

		final JComboBox comboBoxModelo = new JComboBox();
		comboBoxModelo.setBounds(186, 140, 232, 24);
		List<Modelo> modelos = ModeloController.listarModelos();
		comboBoxModelo.addItem("");
		for (Modelo modelo : modelos) {
			comboBoxModelo.addItem(modelo.getNome());
		}
		panel.add(comboBoxModelo);
		
		final JComboBox comboBoxGrupo = new JComboBox();
		comboBoxGrupo.setEnabled(false);
		comboBoxGrupo.setBounds(186, 166, 232, 24);
		List<Grupo> grupos = GrupoController.listarGrupos();
		comboBoxGrupo.addItem("");
		for (Grupo grupo : grupos) {
			comboBoxGrupo.addItem(grupo.getNome());
		}
		panel.add(comboBoxGrupo);
		
		final JComboBox comboBoxFilial = new JComboBox();
		comboBoxFilial.setBounds(186, 19, 232, 24);
		List<Filial> filiais = FilialController.listarFiliais();
		for (Filial filial : filiais) {
			comboBoxFilial.addItem(filial.getNome());
		}
		panel.add(comboBoxFilial);
		

		
		btnSelecionar.setBounds(342, 49, 116, 15);
		contentPane.add(btnSelecionar);
		
		
		JLabel lblDadosDaLocao = new JLabel("Dados da locação");
		lblDadosDaLocao.setBounds(170, 78, 143, 15);
		contentPane.add(lblDadosDaLocao);
		
		JLabel label = new JLabel("Nome da Filial");
		label.setBounds(12, 24, 126, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Devolução");
		label_1.setBounds(12, 115, 155, 15);
		panel.add(label_1);
		
		JButton btnAlocar = new JButton("Alugar");
		btnAlocar.setBounds(151, 327, 117, 25);
		panel.add(btnAlocar);
		
		JLabel label_2 = new JLabel("CPF");
		label_2.setBounds(12, 50, 155, 15);
		panel.add(label_2);
		
		
		JLabel label_3 = new JLabel("Modelo do carro");
		label_3.setBounds(12, 145, 126, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Locação");
		label_4.setBounds(12, 85, 116, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Grupo do carro");
		label_5.setBounds(12, 171, 126, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Motoristas (por cnh)");
		label_6.setBounds(12, 205, 179, 14);
		panel.add(label_6);
		
		txtMotorista1 = new JTextField();
		txtMotorista1.setColumns(10);
		txtMotorista1.setBounds(10, 227, 181, 20);
		panel.add(txtMotorista1);
		
		txtMotorista4 = new JTextField();
		txtMotorista4.setEnabled(false);
		txtMotorista4.setColumns(10);
		txtMotorista4.setBounds(237, 227, 181, 20);
		panel.add(txtMotorista4);
		
		txtMotorista2 = new JTextField();
		txtMotorista2.setEnabled(false);
		txtMotorista2.setColumns(10);
		txtMotorista2.setBounds(10, 258, 181, 20);
		panel.add(txtMotorista2);
		
		txtMotorista5 = new JTextField();
		txtMotorista5.setEnabled(false);
		txtMotorista5.setColumns(10);
		txtMotorista5.setBounds(237, 258, 181, 20);
		panel.add(txtMotorista5);
		
		txtMotorista3 = new JTextField();
		txtMotorista3.setEnabled(false);
		txtMotorista3.setColumns(10);
		txtMotorista3.setBounds(10, 289, 181, 20);
		panel.add(txtMotorista3);
		
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
		
		chckbxTemReserva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxTemReserva.isSelected()) {
					textCpf.setEnabled(false);
					formatted_txtDataDevolucao.setEnabled(false);
					formatted_txtDataLocacao.setEnabled(false);
					txtReservaCpf.setEnabled(true);
					txtMotorista1.setEnabled(false);
					txtMotorista4.setEnabled(false);
					txtMotorista2.setEnabled(false);
					txtMotorista5.setEnabled(false);
					txtMotorista3.setEnabled(false);
					comboBoxFilial.setEnabled(false);
					comboBoxGrupo.setEnabled(false);
					comboBoxModelo.setEnabled(false);
				} else {
					txtReservaCpf.setEnabled(false);
					textCpf.setEnabled(true);
					formatted_txtDataDevolucao.setEnabled(true);
					formatted_txtDataLocacao.setEnabled(true);
					txtMotorista1.setEnabled(true);
					txtMotorista4.setEnabled(true);
					txtMotorista2.setEnabled(true);
					txtMotorista5.setEnabled(true);
					txtMotorista3.setEnabled(true);
					comboBoxFilial.setEnabled(true);
					comboBoxGrupo.setEnabled(true);
					comboBoxModelo.setEnabled(true);
				}

			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btnAlocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CPF = "";
				
				if (chckbxTemReserva.isSelected()) {
					//se tinha reserva previa usamos o CPF dado para buscar a reserva para hoje
					CPF=txtReservaCpf.getText();
				}else{
					//verficando se ja existia uma reserva pro dia, no caso a pessoa nao pode fazer locação imediata
					Reserva reserva = new Reserva();
					Cliente cliente = new Cliente();
					cliente.setCpf(textCpf.getText());
					reserva.setCliente(cliente);
					reserva.setDataLocacao(UtilData.retornaDataSemHora(UtilData.agora()));
					reserva = new Buscador().selecionar(reserva);
					
					if(Util.preenchido(reserva)){
						JOptionPane.showMessageDialog(null, "Ja existe uma reserva para esse dia.");
					}
							
					//se nao tinha reserva pro dia procedemos
					if(!Util.preenchido(reserva)){
						
						//se nao tinha reserva previa vamos criar agora
						CPF= textCpf.getText();
						ReservaController reservaController = new ReservaController();
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
						
						Filial filial = null;
						if(Util.preenchido(nomeFilial)){
							filial = new Filial();
							filial.setNome(nomeFilial);
							filial = new Buscador().selecionar(filial);
						}
						
						Modelo modeloFiltro = null;
						if(Util.preenchido(modelo)){
							modeloFiltro = new Modelo();
							modeloFiltro.setNome(modelo);
							modeloFiltro = new Buscador().selecionar(modeloFiltro);
						}
						
						if(Util.preenchido(filial)){
							if(Util.preenchido(modeloFiltro)){
								if(CarroService.existeCarroDisponivel(modeloFiltro, filial)){
								
									boolean sucesso = reservaController.criarReserva(nomeFilial,
											textCpf.getText(), UtilData.formata( UtilData.retornaDataSemHora(UtilData.agora()),"dd/MM/YYYY"),
											formatted_txtDataDevolucao.getText(), modelo, grupo,
											motoristas);
									
									if (!sucesso) {
										JOptionPane.showMessageDialog(null,
												"Ocorreu um erro na criação da locação.");
									}
								}else{
									JOptionPane.showMessageDialog(null,
											"Não existe nenhum carro disponível para este modelo.");
								}
							}else{
								JOptionPane.showMessageDialog(null,
										"Modelo incorreto.");
							}
						}else{
							JOptionPane.showMessageDialog(null,
									"Filial incorreta.");
						}
					}
				}
				
				boolean sucesso = false;
				if(Util.preenchido(CPF)){
					Reserva reserva = new Reserva();
					Cliente cliente = new Cliente();
					cliente.setCpf(CPF);
					reserva.setCliente(cliente);
					reserva.setDataLocacao(UtilData.retornaDataSemHora(UtilData.agora()));
					reserva = new Buscador().selecionar(reserva);
					sucesso = locacaoController.criarLocacao(reserva);
				}
				
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Locação feita com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação da locação.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnSelecionar);
	}
	
	
}
