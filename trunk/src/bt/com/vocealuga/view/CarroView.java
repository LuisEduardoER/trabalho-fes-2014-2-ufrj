package bt.com.vocealuga.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.vocealuga.controller.CarroController;
import br.com.vocealuga.controller.FilialController;
import br.com.vocealuga.controller.ModeloController;
import br.com.vocealuga.hibernate.Buscador;
import br.com.vocealuga.modelo.entidade.Carro;
import br.com.vocealuga.modelo.entidade.Filial;
import br.com.vocealuga.modelo.entidade.Modelo;
import br.com.vocealuga.util.Util;
import br.com.vocealuga.util.UtilNumero;

@SuppressWarnings("serial")
public class CarroView extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtAno;
	private JTextField txtKm;
	private JTextField txtRevisao;

	/**
	 * Create the frame.
	 */
	public CarroView(final CarroController carroController) {
		setTitle("Criar carro");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("Modelo");
		lblDe.setBounds(10, 24, 86, 15);
		contentPane.add(lblDe);
		
		JButton btnAlocar = new JButton("Salvar");
		this.getRootPane().setDefaultButton(btnAlocar);
		
		final JCheckBox chckbxEstaAdequado = new JCheckBox("Est\u00E1 adequado?");
		chckbxEstaAdequado.setBounds(10, 170, 147, 23);
		contentPane.add(chckbxEstaAdequado);
		
		@SuppressWarnings("rawtypes")
		final
		JComboBox comboBox = new JComboBox();
		List<Modelo> modelos = ModeloController.listarModelos();
		for(Modelo modelo : modelos){
			comboBox.addItem(modelo.getNome());
		}
		comboBox.setBounds(164, 19, 86, 24);
		contentPane.add(comboBox);
		
		final JComboBox comboBoxFilial = new JComboBox();
		List<Filial> filiais = FilialController.listarFiliais();
		for(Filial filial : filiais){
			comboBoxFilial.addItem(filial.getNome());
		}
		
		comboBoxFilial.setBounds(164, 76, 86, 24);
		contentPane.add(comboBoxFilial);
		
		btnAlocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String modelo = (String) comboBox.getSelectedItem();
				String nomeFilial = (String) comboBoxFilial.getSelectedItem();
				
				boolean sucesso = false;
				if(Util.preenchido(modelo) && Util.preenchido(txtAno.getText()) && UtilNumero.isIntPositivo(txtAno.getText()) 
						&& Util.preenchido(nomeFilial) 
						&& Util.preenchido(txtKm.getText()) && UtilNumero.isInt(txtKm.getText())
						&& Util.preenchido(txtRevisao.getText()) && UtilNumero.isIntPositivo(txtRevisao.getText())
						&& Util.preenchido(chckbxEstaAdequado)){
					Carro carro = new Carro();
					carro.setModelo(new Modelo(modelo));
					carro.setAno(new GregorianCalendar(Integer.valueOf(txtAno.getText()), 0,1));
					
					Filial filial = new Filial();
					filial.setNome(nomeFilial);
					filial = new Buscador().selecionar(filial);
					carro.setFilial(filial);
					
					carro.setUltimaRevisao(new GregorianCalendar(Integer.valueOf(txtRevisao.getText()), 0, 1));
					carro.setQuilometragem(Integer.valueOf(txtKm.getText()));
					carro.setEstaAdequado(chckbxEstaAdequado.isSelected());
					
					carro = carroController.criarCarro(carro);
					
					if(Util.preenchido(carro)){
						sucesso = true;
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Preencha os dados corretamente.");
				}
				
				if(sucesso) {
					JOptionPane.showMessageDialog(null, "Carro foi criado com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na cria��o da carro.");
				}
			}
		});
		this.getRootPane().setDefaultButton(btnAlocar);
		
		btnAlocar.setBounds(361, 191, 117, 25);
		contentPane.add(btnAlocar);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(10, 50, 46, 14);
		contentPane.add(lblAno);
		
		txtAno = new JTextField();
		txtAno.setBounds(164, 48, 86, 20);
		contentPane.add(txtAno);
		txtAno.setColumns(10);
		
		JLabel lblIdDaFilial = new JLabel("Filial");
		lblIdDaFilial.setBounds(10, 81, 97, 14);
		contentPane.add(lblIdDaFilial);
		
		JLabel lblKm = new JLabel("Quilometragem");
		lblKm.setBounds(10, 106, 136, 14);
		contentPane.add(lblKm);
		
		txtKm = new JTextField();
		txtKm.setBounds(164, 104, 46, 20);
		contentPane.add(txtKm);
		txtKm.setColumns(10);
		
		JLabel lblAnoltRev = new JLabel("Ano última revisão");
		lblAnoltRev.setBounds(10, 131, 136, 14);
		contentPane.add(lblAnoltRev);
		
		txtRevisao = new JTextField();
		txtRevisao.setBounds(164, 129, 46, 20);
		contentPane.add(txtRevisao);
		txtRevisao.setColumns(10);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
