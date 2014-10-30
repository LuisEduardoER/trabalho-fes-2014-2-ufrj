package bt.com.vocealuga.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.vocealuga.controller.PagamentoController;
import br.com.vocealuga.modelo.auxiliar.TipoPagamento;
import br.com.vocealuga.modelo.entidade.Pagamento;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilNumero;

@SuppressWarnings("serial")
public class PagamentoView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtIdReserva;
	private JTextField txtValor;

	/**
	 * Create the frame.
	 */
	public PagamentoView(final PagamentoController pagamentoController) {
		setTitle("Criar pagamento");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Id da Reserva");
		lblDe.setBounds(10, 24, 86, 15);
		contentPane.add(lblDe);
		
		txtIdReserva = new JTextField();
		txtIdReserva.setBounds(108, 21, 78, 19);
		contentPane.add(txtIdReserva);
		txtIdReserva.setColumns(10);
		
		JButton btnAlocar = new JButton("Verificar");
		this.getRootPane().setDefaultButton(btnAlocar);
		
		final JComboBox<TipoPagamento> comboBox = new JComboBox<TipoPagamento>();
		comboBox.setEnabled(false);
		comboBox.setBounds(127, 107, 86, 20);
		contentPane.add(comboBox);
		
		comboBox.addItem(TipoPagamento.CHEQUE);
		comboBox.addItem(TipoPagamento.DINHEIRO);
		comboBox.addItem(TipoPagamento.CREDITO);
		comboBox.addItem(TipoPagamento.DEBITO);
		
		final JButton btnPagar = new JButton("Pagar");
		btnPagar.setEnabled(false);
		btnPagar.setBounds(389, 140, 78, 25);
		contentPane.add(btnPagar);
		
		btnAlocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean sucesso = false;
				if(Util.preenchido(txtIdReserva.getText()) && UtilNumero.isIntPositivo(txtIdReserva.getText())){
					Reserva reserva = new Reserva();
					reserva.setId(Integer.valueOf(txtIdReserva.getText()));
					
					Pagamento pagamento = PagamentoController.selecionarValorPagamento(reserva);
					if(Util.preenchido(pagamento)){
						sucesso = true;
						txtValor.setText(String.valueOf(pagamento.getValor()));
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Preencha os dados corretamente.");
				}
				
				if(sucesso) {
					txtValor.setEnabled(true);
					comboBox.setEnabled(true);
					btnPagar.setEnabled(true);
					getRootPane().setDefaultButton(btnPagar);
					txtIdReserva.setEnabled(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação da pagamento.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnAlocar);
		
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean sucesso = false;
				if(Util.preenchido(txtValor.getText()) && UtilNumero.isBigDecimal(txtValor.getText())
						&& Util.preenchido(comboBox) && Util.preenchido(comboBox.getSelectedItem())
						&& Util.preenchido(txtIdReserva.getText()) && UtilNumero.isIntPositivo(txtIdReserva.getText())){
					
					Pagamento pagamento = new Pagamento();
					Reserva reserva = new Reserva();
					reserva.setId(Integer.valueOf(txtIdReserva.getText()));
					
					pagamento.setReserva(reserva);
					pagamento.setValor(new BigDecimal(txtValor.getText()));
					pagamento.setTipoPagamento((TipoPagamento) comboBox.getSelectedItem());
					
					pagamento = pagamentoController.efetuarPagamento(pagamento);
					
					if(Util.preenchido(pagamento)){
						sucesso = true;
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Preencha os dados corretamente.");
				}
				
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Pagamento efetuado com sucesso!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na criação da pagamento.");
				}
			}
		});
		
		btnAlocar.setBounds(196, 19, 78, 25);
		contentPane.add(btnAlocar);
		
		JLabel lblAno = new JLabel("Valor");
		lblAno.setBounds(10, 79, 46, 14);
		contentPane.add(lblAno);
		
		txtValor = new JTextField();
		txtValor.setEnabled(false);
		txtValor.setBounds(127, 76, 86, 20);
		contentPane.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblIdDaFilial = new JLabel("Forma de Pagamento");
		lblIdDaFilial.setBounds(10, 110, 102, 14);
		contentPane.add(lblIdDaFilial);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
