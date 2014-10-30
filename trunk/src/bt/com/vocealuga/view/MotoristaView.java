package bt.com.vocealuga.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import br.com.vocealuga.controller.MotoristaController;
import br.com.vocealuga.modelo.entidade.Motorista;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilData;
import br.com.vocealuga.util.UtilNumero;

@SuppressWarnings("serial")
public class MotoristaView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCnh;
	private JTextField txtNumApolice;
	private JCheckBox chckbxDeficiente;
	private JFormattedTextField formatted_txtData;
	private JFormattedTextField formatted_txtValidadeCNH;

	/**
	 * Create the frame.
	 */
	public MotoristaView(final MotoristaController motoristaController) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Nome");
		lblDe.setBounds(12, 54, 70, 15);
		contentPane.add(lblDe);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(12, 115, 155, 15);
		contentPane.add(lblDataDeNascimento);
		
		txtNome = new JTextField();
		txtNome.setText("Nome");
		txtNome.setBounds(186, 52, 232, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCnh.getText().startsWith("0") || !UtilNumero.isNumeroInteiro(txtCnh.getText()) || txtCnh.getText().contains(".")){
					JOptionPane.showMessageDialog(null, "Cnh inválido.");
				}
				else if(txtNumApolice.getText().startsWith("0")|| !UtilNumero.isNumeroInteiro(txtNumApolice.getText()) || txtNumApolice.getText().contains(".")){
					JOptionPane.showMessageDialog(null, "Apólice inválida.");
				}
				else if(!UtilData.isDataValida(formatted_txtData.getText()) || formatted_txtData.getText().startsWith("00")){
					JOptionPane.showMessageDialog(null, "Data de nascimento inválida.");
				}
				else if(!UtilData.isDataValida(formatted_txtValidadeCNH.getText()) || formatted_txtValidadeCNH.getText().startsWith("00")){
					JOptionPane.showMessageDialog(null, "Data de validade inválida.");
				}
				else{
					boolean sucesso = motoristaController.criarMotorista(txtNome.getText(), txtCnh.getText(), formatted_txtValidadeCNH.getText(), formatted_txtData.getText(), txtNumApolice.getText(), chckbxDeficiente.isSelected());
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Motorista criado com sucesso.");
					}
					else {
						JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação do motorista.");
					}
				}
			}
		});
		btnCadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		btnCadastrar.setBounds(153, 197, 117, 25);
		contentPane.add(btnCadastrar);
		
		JLabel lblCnh = new JLabel("CNH");
		lblCnh.setBounds(12, 22, 155, 15);
		contentPane.add(lblCnh);
		
		txtCnh = new JTextField();
		txtCnh.setText("00000000000");
		txtCnh.setBounds(186, 21, 138, 19);
		contentPane.add(txtCnh);
		txtCnh.setColumns(10);
		
		txtNumApolice = new JTextField();
		txtNumApolice.setText("000000");
		txtNumApolice.setBounds(186, 142, 232, 19);
		contentPane.add(txtNumApolice);
		txtNumApolice.setColumns(10);
		
		JLabel lblNApolice = new JLabel("N\u00FAmero Apolice");
		lblNApolice.setBounds(12, 145, 126, 15);
		contentPane.add(lblNApolice);
		
		JLabel lblNewLabel = new JLabel("Validade CNH");
		lblNewLabel.setBounds(12, 85, 116, 15);
		contentPane.add(lblNewLabel);
		
		chckbxDeficiente = new JCheckBox("");
		chckbxDeficiente.setBounds(186, 167, 129, 23);
		contentPane.add(chckbxDeficiente);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter df = new DateFormatter(format);
        
		formatted_txtData = new JFormattedTextField(df);
		formatted_txtData.setText("00/00/0000");
		formatted_txtData.setBounds(186, 112, 232, 20);
		contentPane.add(formatted_txtData);
		
		JLabel lblDeficiente = new JLabel("Deficiente?");
		lblDeficiente.setBounds(12, 171, 126, 15);
		contentPane.add(lblDeficiente);
		
		formatted_txtValidadeCNH = new JFormattedTextField(df);
		formatted_txtValidadeCNH.setText("00/00/0000");
		formatted_txtValidadeCNH.setBounds(186, 82, 232, 20);
		contentPane.add(formatted_txtValidadeCNH);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Motorista motorista = motoristaController.selecionarMotorista(txtCnh.getText());
				if(Util.preenchido(motorista)){
					txtNumApolice.setText(motorista.getNumeroApolice());
					txtNome.setText(motorista.getNome());
					formatted_txtData.setText(UtilData.formata(motorista.getDataNascimento(),"dd/MM/yyyy"));
					formatted_txtValidadeCNH.setText(UtilData.formata(motorista.getDataNascimento(),"dd/MM/yyyy"));
					chckbxDeficiente.setSelected(motorista.getDeficiente());
				}else{
					txtNumApolice.setText("00000000000");
					txtNome.setText("Nome Completo");
					formatted_txtData.setText("00/00/0000");
					formatted_txtValidadeCNH.setText("00/00/0000");
					chckbxDeficiente.setSelected(false);
				}
			}
		});
		btnBuscar.setBounds(336, 22, 82, 15);
		contentPane.add(btnBuscar);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
