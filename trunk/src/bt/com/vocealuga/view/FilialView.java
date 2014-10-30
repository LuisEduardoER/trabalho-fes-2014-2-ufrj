package bt.com.vocealuga.view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.com.vocealuga.controller.FilialController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FilialView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtNome;

	/**
	 * Create the frame.
	 */
	public FilialView(final FilialController filialController) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 122);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Nome da filial");
		lblDe.setBounds(12, 24, 117, 15);
		contentPane.add(lblDe);
		
		txtNome = new JTextField();
		txtNome.setText("Filial1");
		txtNome.setBounds(147, 22, 271, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				boolean sucesso = filialController.criarFilial(txtNome.getText());
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Filial criada com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação da filial.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnCadastrar);
		
		btnCadastrar.setBounds(137, 52, 117, 25);
		contentPane.add(btnCadastrar);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
