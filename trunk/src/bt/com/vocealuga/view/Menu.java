package bt.com.vocealuga.view;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import br.com.vocealuga.controller.AdministrativoController;
import br.com.vocealuga.controller.CarroController;
import br.com.vocealuga.controller.ClienteController;
import br.com.vocealuga.controller.FilialController;
import br.com.vocealuga.controller.GrupoController;
import br.com.vocealuga.controller.ListaNegraController;
import br.com.vocealuga.controller.LocacaoController;
import br.com.vocealuga.controller.ModeloController;
import br.com.vocealuga.controller.MotoristaController;
import br.com.vocealuga.controller.PagamentoController;
import br.com.vocealuga.controller.ReservaController;
import br.com.vocealuga.modelo.entidade.Reserva;
import br.com.vocealuga.util.Util;


public class Menu {
	
	private JFrame frmMenu;
	private ClienteController clienteController = new ClienteController();
	private ReservaController reservaController = new ReservaController();
	private MotoristaController motoristaController = new MotoristaController();
	private FilialController filialController = new FilialController();
	private GrupoController grupoController = new GrupoController();
	private ModeloController modeloController = new ModeloController();
	private LocacaoController locacaoController = new LocacaoController();
	private ListaNegraController listaNegraController = new ListaNegraController();
	private CarroController carroController = new CarroController();
	private AdministrativoController administrativoController = new AdministrativoController();
	private PagamentoController pagamentoController = new PagamentoController();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		initialize();
	}

	private void initialize() {
		
		frmMenu = new JFrame();
		frmMenu.setBounds(100, 100, 488, 498);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(29, 48, 391, 87);
		frmMenu.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCadastrarCliente = new JButton("Cliente");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClienteView clienteView = new ClienteView(clienteController);
				clienteView.setVisible(true);
			}
		});
		btnCadastrarCliente.setBounds(12, 12, 105, 25);
		panel.add(btnCadastrarCliente);
		
		JButton btnModelo = new JButton("Modelo");
		btnModelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ModeloView modeloView = new ModeloView(modeloController);
				modeloView.setVisible(true);
			}
		});
		btnModelo.setBounds(129, 12, 117, 25);
		panel.add(btnModelo);
		
		JButton btnFilial = new JButton("Filial");
		btnFilial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FilialView filialView = new FilialView(filialController);
				filialView.setVisible(true);
			}
		});
		btnFilial.setBounds(258, 12, 117, 25);
		panel.add(btnFilial);
		
		JButton btnMotorista = new JButton("Motorista");
		btnMotorista.setBounds(12, 49, 105, 25);
		panel.add(btnMotorista);
		btnMotorista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MotoristaView motoristaView = new MotoristaView(motoristaController);
				motoristaView.setVisible(true);
			}
		});
		
		JButton btnCarro = new JButton("Carro");
		btnCarro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CarroView carroView = new CarroView(carroController);
				carroView.setVisible(true);
			}
		});
		btnCarro.setBounds(129, 49, 117, 25);
		panel.add(btnCarro);
		
		JButton btnGrupo = new JButton("Grupo");
		btnGrupo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GrupoView grupoView = new GrupoView(grupoController);
				grupoView.setVisible(true);
			}
		});
		btnGrupo.setBounds(258, 49, 117, 25);
		panel.add(btnGrupo);
		
		JLabel lblCliente = new JLabel("Cadastro");
		lblCliente.setBounds(29, 31, 70, 15);
		frmMenu.getContentPane().add(lblCliente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(29, 169, 391, 52);
		frmMenu.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEfetuarReserva = new JButton("Efetuar reserva");
		btnEfetuarReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservaView reservaView = new ReservaView(reservaController);
				reservaView.setVisible(true);
			}
		});
		btnEfetuarReserva.setBounds(12, 12, 144, 25);
		panel_1.add(btnEfetuarReserva);
		
		JButton btnEfetuarLocao = new JButton("Efetuar locação");
		btnEfetuarLocao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocacaoView locacaoView = new LocacaoView(locacaoController);
				locacaoView.setVisible(true);
			}
		});
		btnEfetuarLocao.setBounds(235, 12, 144, 25);
		panel_1.add(btnEfetuarLocao);
		
		JLabel lblLocao = new JLabel("Locação");
		lblLocao.setBounds(39, 153, 70, 15);
		frmMenu.getContentPane().add(lblLocao);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(29, 257, 391, 52);
		frmMenu.getContentPane().add(panel_2);
		
		JButton btnRegistrarDevoluo = new JButton("Registrar devolução");
		btnRegistrarDevoluo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DevolucaoView devolucaoView = new DevolucaoView(locacaoController);
				devolucaoView.setVisible(true);
			}
		});
		btnRegistrarDevoluo.setBounds(12, 12, 176, 25);
		panel_2.add(btnRegistrarDevoluo);
		
		JButton btnEfetuarPagamento = new JButton("Efetuar pagamento");
		btnEfetuarPagamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PagamentoView pagamentoView = new PagamentoView(pagamentoController);
				pagamentoView.setVisible(true);
			}
		});
		btnEfetuarPagamento.setBounds(200, 12, 179, 25);
		panel_2.add(btnEfetuarPagamento);
		
		JLabel lblDevoluo = new JLabel("Devolução");
		lblDevoluo.setBounds(39, 241, 82, 15);
		frmMenu.getContentPane().add(lblDevoluo);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(29, 334, 391, 52);
		frmMenu.getContentPane().add(panel_3);
		
		JButton btnAlocarCarros = new JButton("Alocar carros");
		btnAlocarCarros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					List<Reserva> reservas = administrativoController.alocarReservas();
					if(Util.preenchido(reservas)){
						JOptionPane.showMessageDialog(null, "Reservas alocadas com sucesso. Algumas reservas n�o tiveram carros suficientes. Sugerimos que contate outra filial.");
					}else{
						JOptionPane.showMessageDialog(null, "Todas as reservas foram alocadas com sucesso.");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro na requisi��o.");
				}
				
				
			}
		});
		btnAlocarCarros.setBounds(12, 12, 176, 25);
		panel_3.add(btnAlocarCarros);
		
		JButton btnAdicionarListaNegra = new JButton("Lista negra");
		btnAdicionarListaNegra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListaNegraView listaNegraView = new ListaNegraView(listaNegraController);
				listaNegraView.setVisible(true);
			}
		});
		btnAdicionarListaNegra.setBounds(200, 12, 179, 25);
		panel_3.add(btnAdicionarListaNegra);
		
		JLabel lblAes = new JLabel("Ações");
		lblAes.setBounds(39, 318, 82, 15);
		frmMenu.getContentPane().add(lblAes);
	}
}
