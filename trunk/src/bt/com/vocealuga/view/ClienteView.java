package bt.com.vocealuga.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import br.com.vocealuga.controller.ClienteController;
import br.com.vocealuga.modelo.entidade.Cliente;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;
import br.com.vocealuga.util.UtilNumero;

@SuppressWarnings("serial")
public class ClienteView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtCartao;
	private JFormattedTextField formatted_txtData;

	/**
	 * Create the frame.
	 */
	public ClienteView(final ClienteController clienteController) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Nome");
		lblDe.setBounds(12, 59, 70, 15);
		contentPane.add(lblDe);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(12, 86, 155, 15);
		contentPane.add(lblDataDeNascimento);
		
		JLabel lblCartaoDeCredito = new JLabel("Cartao de Credito");
		lblCartaoDeCredito.setBounds(12, 113, 155, 15);
		contentPane.add(lblCartaoDeCredito);
		
		txtNome = new JTextField();
		txtNome.setText("Nome");
		txtNome.setBounds(186, 57, 232, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setText("00000000000");
		txtCPF.setBounds(186, 30, 117, 19);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(12, 32, 70, 15);
		contentPane.add(lblCpf);
		
		txtCartao = new JTextField();
		txtCartao.setText("0000000000000000");
		txtCartao.setBounds(186, 111, 232, 19);
		contentPane.add(txtCartao);
		txtCartao.setColumns(10);
		

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter df = new DateFormatter(format);
		
		formatted_txtData = new JFormattedTextField(df);
		formatted_txtData.setText("00/00/0000");
		formatted_txtData.setBounds(186, 83, 232, 20);
		contentPane.add(formatted_txtData);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCartao.getText().startsWith("0") || !UtilNumero.isNumeroInteiro(txtCartao.getText()) || txtCartao.getText().contains(".")){
					JOptionPane.showMessageDialog(null, "Cartão inválido.");
				}
				else if(txtCPF.getText().startsWith("0")|| !UtilNumero.isNumeroInteiro(txtCPF.getText()) || txtCPF.getText().contains(".")){
					JOptionPane.showMessageDialog(null, "Cpf inválido.");
				}
				else if(!UtilData.isDataValida(formatted_txtData.getText()) || formatted_txtData.getText().startsWith("00")){
					JOptionPane.showMessageDialog(null, "Data inválida.");
				}
				else{
					boolean sucesso = clienteController.criarCliente(txtNome.getText(), txtCPF.getText(), txtCartao.getText(), formatted_txtData.getText());
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Cliente criado com sucesso.");
					}
					else {
						JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação do cliente.");
					}
				}
			}
		});
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.setBounds(147, 156, 117, 25);
		contentPane.add(btnCadastrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(315, 30, 98, 15);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Cliente cliente = clienteController.selecionarCliente(txtCPF.getText());
				if(Util.preenchido(cliente)){
					txtCartao.setText(cliente.getCartao());
					txtNome.setText(cliente.getNome());
					formatted_txtData.setText(UtilData.formata(cliente.getDataNascimento(),"dd/MM/yyyy"));
				}else{
					txtCartao.setText("0000000000000000");
					txtNome.setText("Nome Completo");
					formatted_txtData.setText("00/00/0000");
				}
			}
		});
		contentPane.add(btnBuscar);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
