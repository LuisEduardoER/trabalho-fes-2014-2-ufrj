package bt.com.vocealuga.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.vocealuga.controller.GrupoController;
import br.com.vocealuga.controller.ModeloController;
import br.com.vocealuga.modelo.entidade.Grupo;

@SuppressWarnings("serial")
public class ModeloView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel label;

	/**
	 * Create the frame.
	 */
	public ModeloView(final ModeloController modeloController) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Nome do modelo");
		lblDe.setBounds(12, 24, 132, 15);
		contentPane.add(lblDe);
		
		txtNome = new JTextField();
		txtNome.setText("Modelo1");
		txtNome.setBounds(140, 21, 294, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		@SuppressWarnings("rawtypes")
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(140, 47, 132, 24);
		List<Grupo> grupos = GrupoController.listarGrupos();
		for(Grupo grupo : grupos){
			comboBox.addItem(grupo.getNome());
		}
		contentPane.add(comboBox);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String grupo = (String) comboBox.getSelectedItem();
				boolean sucesso = modeloController.criarModelo(txtNome.getText(), grupo);
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Modelo foi criado com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação do modelo.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.setBounds(140, 101, 117, 25);
		contentPane.add(btnCadastrar);
		
		label = new JLabel("Nome do grupo");
		label.setBounds(22, 51, 132, 16);
		contentPane.add(label);
		

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
