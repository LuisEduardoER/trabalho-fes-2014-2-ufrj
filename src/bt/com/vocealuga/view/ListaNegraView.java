package bt.com.vocealuga.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.com.vocealuga.controller.ListaNegraController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListaNegraView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtCpf;
	private JLabel lblMotivo;
	private JTextField txtMotivo;

	/**
	 * Create the frame.
	 */
	public ListaNegraView(final ListaNegraController listaNegraController) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Informe CPF");
		lblDe.setBounds(12, 24, 86, 15);
		contentPane.add(lblDe);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(108, 21, 326, 19);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				boolean sucesso = listaNegraController.criarListaNegra(txtCpf.getText(), txtMotivo.getText());
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Adicionado a lista negra com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na adição a lista negra.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.setBounds(156, 105, 117, 25);
		contentPane.add(btnCadastrar);
		
		lblMotivo = new JLabel("Motivo");
		lblMotivo.setBounds(12, 64, 86, 15);
		contentPane.add(lblMotivo);
		
		txtMotivo = new JTextField();
		txtMotivo.setBounds(108, 61, 326, 20);
		contentPane.add(txtMotivo);
		txtMotivo.setColumns(10);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
