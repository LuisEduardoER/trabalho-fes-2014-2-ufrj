package bt.com.vocealuga.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.vocealuga.controller.GrupoController;
import br.com.vocealuga.modelo.entidade.Grupo;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilNumero;

@SuppressWarnings("serial")
public class GrupoView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblPrioridade;
	private JTextField txtPrioridade;
	private JCheckBox chckbxGrupoPadrao;
	private JLabel lblValorDiaria;
	private JTextField textValorDiaria;

	/**
	 * Create the frame.
	 */
	public GrupoView(final GrupoController grupoController) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Nome do grupo");
		lblDe.setBounds(12, 24, 119, 15);
		contentPane.add(lblDe);
		
		txtNome = new JTextField();
		txtNome.setText("Grupo1");
		txtNome.setBounds(149, 21, 285, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso = false;
				if(Util.preenchido(txtNome.getText()) && Util.preenchido(chckbxGrupoPadrao) && Util.preenchido(txtPrioridade.getText()) && UtilNumero.isIntPositivo(txtPrioridade.getText()) && Util.preenchido(textValorDiaria.getText())){
					Grupo grupo = new Grupo();
					grupo.setNome(txtNome.getText());
					grupo.setPadrao(chckbxGrupoPadrao.isSelected());
					grupo.setPrioridade(Integer.valueOf(txtPrioridade.getText()));
					grupo.setValorDiaria(new BigDecimal(textValorDiaria.getText()));
					
					sucesso = grupoController.criarGrupo(grupo);
				}else{
					JOptionPane.showMessageDialog(null, "Preencha os dados corretamente.");
				}
				
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Grupo foi criado com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na cria��o do grupo.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.setBounds(345, 127, 117, 25);
		contentPane.add(btnCadastrar);
		
		lblPrioridade = new JLabel("Prioridade (>1)");
		lblPrioridade.setBounds(10, 50, 121, 15);
		contentPane.add(lblPrioridade);
		
		txtPrioridade = new JTextField();
		txtPrioridade.setBounds(149, 47, 45, 20);
		contentPane.add(txtPrioridade);
		txtPrioridade.setColumns(10);
		
		chckbxGrupoPadrao = new JCheckBox("Grupo padr\u00E3o?");
		chckbxGrupoPadrao.setBounds(21, 128, 142, 23);
		contentPane.add(chckbxGrupoPadrao);
		
		lblValorDiaria = new JLabel("Valor da di\u00E1ria");
		lblValorDiaria.setBounds(12, 81, 110, 14);
		contentPane.add(lblValorDiaria);
		
		textValorDiaria = new JTextField();
		textValorDiaria.setBounds(149, 79, 45, 20);
		contentPane.add(textValorDiaria);
		textValorDiaria.setColumns(10);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
