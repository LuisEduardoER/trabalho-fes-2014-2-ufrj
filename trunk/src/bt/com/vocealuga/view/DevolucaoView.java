package bt.com.vocealuga.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.vocealuga.controller.LocacaoController;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilNumero;

@SuppressWarnings("serial")
public class DevolucaoView extends JFrame {
	
	private JPanel contentPane;
	private JLabel lblIdFilialDevolucao;
	private JTextField txtIdFilial;
	private JTextField txtIdReserva;

	/**
	 * Create the frame.
	 */
	public DevolucaoView(final LocacaoController locacaoController) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDevolver = new JButton("Devolver");
		this.getRootPane().setDefaultButton(btnDevolver);
		
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso = false;
				if(Util.preenchido(txtIdFilial.getText()) && UtilNumero.isIntPositivo(txtIdFilial.getText()) && Util.preenchido(txtIdReserva.getText()) && UtilNumero.isIntPositivo(txtIdReserva.getText())){
					Reserva reserva = new Reserva();
					reserva.setId(Integer.valueOf(txtIdReserva.getText()));
					
					Filial filial = new Filial();
					filial.setId(Integer.valueOf(txtIdFilial.getText()));
					
					reserva.setFilialDevolucao(filial);
					
					sucesso = locacaoController.devolver(reserva);
				}else{
					JOptionPane.showMessageDialog(null, "Preencha os dados corretamente.");
				}
				
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Devolução feita com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na devolução.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnDevolver);
		
		btnDevolver.setBounds(345, 69, 117, 25);
		contentPane.add(btnDevolver);
		
		lblIdFilialDevolucao = new JLabel("Id da Filial");
		lblIdFilialDevolucao.setBounds(10, 14, 75, 14);
		contentPane.add(lblIdFilialDevolucao);
		
		txtIdFilial = new JTextField();
		txtIdFilial.setBounds(108, 11, 86, 20);
		contentPane.add(txtIdFilial);
		txtIdFilial.setColumns(10);
		
		JLabel lblIdReserva = new JLabel("Id da Reserva");
		lblIdReserva.setBounds(12, 39, 86, 14);
		contentPane.add(lblIdReserva);
		
		txtIdReserva = new JTextField();
		txtIdReserva.setBounds(108, 36, 86, 20);
		contentPane.add(txtIdReserva);
		txtIdReserva.setColumns(10);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
