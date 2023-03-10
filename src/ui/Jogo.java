package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Bd.conexaoBD;
import negocio.Jogador;
import negocio.Partida;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;
import javax.swing.JEditorPane;
import javax.swing.DropMode;

public class Jogo {

	private JFrame frame;
	private JTextField txtJogador3;
	private JTextField txtJogador2;
	private JTextField txtJogador1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jogo window = new Jogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<JLabel> partidas = new ArrayList<JLabel>();
		ArrayList<JRadioButton> btsPartidas = new ArrayList<JRadioButton>();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./Images/iconJogo.png"));
		frame.setBounds(100, 100, 1048, 425);
		frame.setTitle("Puzzle-Game POO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		Partida partida = new Partida(jogadores, 1);

		// Inicializa????o dos paineis //

		JPanel painel_menu = new JPanel();
		tabbedPane.addTab("MENU", null, painel_menu, null);
		painel_menu.setLayout(null);

		JPanel painel_jogo = new JPanel();
		tabbedPane.addTab("JOGO", null, painel_jogo, null);
		painel_jogo.setLayout(null);

		JPanel painel_carregar_jogo = new JPanel();
		tabbedPane.addTab("Partidas Anteriores", null, painel_carregar_jogo, null);
		painel_carregar_jogo.setLayout(null);
		
		JPanel painel_historico = new JPanel();
		tabbedPane.addTab("Hist??rico", null, painel_historico, null);
		painel_historico.setLayout(null);

		Tabuleiro painel_tabuleiro = partida.tabuleiro;
		painel_tabuleiro.setBounds(0, 28, 544, 319);
		painel_jogo.add(painel_tabuleiro);

		// Inicializa????o dos paineis //

		// Titulo dos paineis //
		JLabel lbTitulo = new JLabel("MENU INICIAL");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbTitulo.setBounds(231, 109, 543, 23);
		painel_menu.add(lbTitulo);

		JLabel lblNewLabel_2 = new JLabel("ESCOLHA A PARTIDA QUE DESEJA RETOMAR:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 27));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(111, 11, 819, 32);
		painel_carregar_jogo.add(lblNewLabel_2);
		// Titulo dos paineis //

		// Inicializa????o Bot??es Menu principal //

		JLabel lblNewLabel = new JLabel("Opcional:");
		lblNewLabel.setBounds(395, 234, 66, 14);
		painel_menu.add(lblNewLabel);

		JRadioButton rdbtnEmbaralharImpar = new JRadioButton("EMBARALHAR IMPAR");
		rdbtnEmbaralharImpar.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEmbaralharImpar.setBounds(446, 230, 164, 23);
		painel_menu.add(rdbtnEmbaralharImpar);

		JButton btnVerHistrico = new JButton("VER HIST??RICO");
		btnVerHistrico.setBounds(790, 176, 185, 23);
		painel_menu.add(btnVerHistrico);

		JButton btnLimparHistorico = new JButton("LIMPAR HIST??RICO");
		btnLimparHistorico.setBounds(790, 202, 185, 23);
		painel_menu.add(btnLimparHistorico);

		JButton btnNovoJogo = new JButton("Novo Jogo");
		btnNovoJogo.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNovoJogo.setBounds(403, 260, 206, 51);
		painel_menu.add(btnNovoJogo);

		JButton btContinuarJogo = new JButton("Carregar Partida Anterior");
		btContinuarJogo.setFont(new Font("Arial", Font.PLAIN, 13));
		btContinuarJogo.setBounds(388, 319, 242, 23);
		painel_menu.add(btContinuarJogo);

		// Inicializa????o Bot??es Menu principal //

		// Imagens menu principal //
		JLabel lbImageTitulo = new JLabel("");
		ImageIcon icon2 = new ImageIcon(
				new ImageIcon("./Images/Title.png").getImage().getScaledInstance(400, 91, Image.SCALE_DEFAULT));
		lbImageTitulo.setIcon(icon2);
		lbImageTitulo.setBounds(312, 0, 400, 95);
		painel_menu.add(lbImageTitulo);
		
		JLabel lbBG = new JLabel("");
		ImageIcon icon3 = new ImageIcon(
				new ImageIcon("./Images/4.png").getImage().getScaledInstance(1034, 300, Image.SCALE_DEFAULT));
		lbBG.setIcon(icon3);
		lbBG.setBounds(0, 55, 1034, 311);
		painel_menu.add(lbBG);

		JLabel lbBG2 = new JLabel("");
		ImageIcon icon4 = new ImageIcon(
				new ImageIcon("./Images/5.png").getImage().getScaledInstance(145, 132, Image.SCALE_DEFAULT));
		lbBG2.setIcon(icon4);
		lbBG2.setBounds(732, -46, 180, 156);
		painel_menu.add(lbBG2);
		
		// Imagens menu principal //

		// Inicializa????o Bot??es Jogo //

		JButton btTrocarFoto = new JButton("Trocar Foto");
		btTrocarFoto.setFont(new Font("Arial", Font.BOLD, 13));
		btTrocarFoto.setBounds(569, 207, 164, 34);
		painel_jogo.add(btTrocarFoto);

		JButton btVoltar = new JButton("Voltar ao Menu");
		btVoltar.setFont(new Font("Arial", Font.PLAIN, 14));
		btVoltar.setBounds(803, 261, 164, 23);
		painel_jogo.add(btVoltar);
		
	
		JLabel lbImgCompleta = new JLabel("");
		lbImgCompleta.setBounds(559, 6, 186, 187);
		painel_jogo.add(lbImgCompleta);
		
		ImageIcon icon = new ImageIcon(
				new ImageIcon("./Images/" + 1 + ".png").getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
		lbImgCompleta.setIcon(icon);

		JLabel lbSelecioneImg = new JLabel("Selecione a imagem:");
		lbSelecioneImg.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbSelecioneImg.setHorizontalAlignment(SwingConstants.CENTER);
		lbSelecioneImg.setBounds(569, 206, 149, 23);
		lbSelecioneImg.setVisible(false);
		painel_jogo.add(lbSelecioneImg);

		// Inicializa????o Bot??es Jogo //
		Cronometro painel_cronometro = partida.cronometro;
		painel_cronometro.setBounds(650, 28, 544, 319);
		
		
		JButton btSalvarJogo = new JButton("Salvar Jogo");
		btSalvarJogo.setFont(new Font("Arial", Font.PLAIN, 12));
		btSalvarJogo.setBounds(161, 66, 150, 23);
		painel_cronometro.add(btSalvarJogo);
		
		JRadioButton rdBtManoelGomes = new JRadioButton("Manoel Gomes");
		rdBtManoelGomes.setVisible(false);
		buttonGroup.add(rdBtManoelGomes);
		rdBtManoelGomes.setBounds(569, 236, 149, 23);
		painel_jogo.add(rdBtManoelGomes);

		JRadioButton rdBtOrochi = new JRadioButton("Orochi");
		rdBtOrochi.setVisible(false);
		buttonGroup.add(rdBtOrochi);
		rdBtOrochi.setBounds(569, 261, 149, 23);
		painel_jogo.add(rdBtOrochi);

		JRadioButton rdBtFenda = new JRadioButton("Fenda Do B??quini");
		rdBtFenda.setVisible(false);
		buttonGroup.add(rdBtFenda);
		rdBtFenda.setBounds(569, 287, 149, 23);
		painel_jogo.add(rdBtFenda);
		
		JButton btNextPlayer = new JButton("Pr??ximo Jogador");
		btNextPlayer.setVisible(false);
		btNextPlayer.setFont(new Font("Arial", Font.PLAIN, 13));
		btNextPlayer.setBounds(803, 31, 164, 23);
		painel_jogo.add(btNextPlayer);
		painel_jogo.add(painel_cronometro);
		
		JButton btAutoResolve = new JButton("Auto-Resolve");
		btAutoResolve.setFont(new Font("Arial", Font.PLAIN, 13));
		btAutoResolve.setBounds(124, 189, 219, 25);
		painel_cronometro.add(btAutoResolve);

		// Inicializa????o Bot??es Jogo //

		// Inicializa????o Bot??es Carregar Jogo //

		JButton btnNewButton_1 = new JButton("Carregar Partida");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_1.setBounds(353, 272, 295, 43);
		painel_carregar_jogo.add(btnNewButton_1);

		JButton btVoltarTela = new JButton("Voltar ao Menu");
		btVoltarTela.setBounds(410, 324, 192, 23);
		painel_carregar_jogo.add(btVoltarTela);
		btVoltarTela.setBounds(410, 324, 192, 23);

		// Inicializa????o Bot??es Carregar Jogo //
		
		// Inicializa????o Bot??es Hist??rico //
		
		JButton btVoltarTela_1 = new JButton("Voltar ao Menu");
		btVoltarTela_1.setBounds(413, 324, 192, 23);
		painel_historico.add(btVoltarTela_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBackground(new Color(222, 221, 218));
		editorPane.setEditable(false);
		editorPane.setFont(new Font("Arial", Font.PLAIN, 12));
		editorPane.setBounds(373, 11, 257, 302);
		painel_historico.add(editorPane);
		
		// Inicializa????o Bot??es Hist??rico //

		// Labels
		JLabel lblNewLabel_1 = new JLabel("Jogador Atual:");
		lblNewLabel_1.setBounds(10, 6, 98, 14);
		painel_jogo.add(lblNewLabel_1);

		JLabel lbJogadorAtual = new JLabel();
		lbJogadorAtual.setFont(new Font("Arial", Font.BOLD, 12));
		lbJogadorAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lbJogadorAtual.setBounds(79, 0, 98, 26);
		painel_jogo.add(lbJogadorAtual);
		// Labels

		// Inser????o de jogadores //

		JLabel lblJogador = new JLabel("Jogador 3:");
		lblJogador.setBounds(388, 193, 80, 14);
		painel_menu.add(lblJogador);

		txtJogador3 = new JTextField();
		txtJogador3.setColumns(10);
		txtJogador3.setBounds(467, 193, 143, 20);
		painel_menu.add(txtJogador3);

		txtJogador2 = new JTextField();
		txtJogador2.setColumns(10);
		txtJogador2.setBounds(467, 168, 143, 20);
		painel_menu.add(txtJogador2);

		JLabel lbJogador2 = new JLabel("Jogador 2:");
		lbJogador2.setBounds(388, 168, 80, 14);
		painel_menu.add(lbJogador2);

		JLabel lbJogador1 = new JLabel("Jogador 1:");
		lbJogador1.setBounds(388, 143, 80, 14);
		painel_menu.add(lbJogador1);

		txtJogador1 = new JTextField();
		txtJogador1.setColumns(10);
		txtJogador1.setBounds(467, 143, 143, 20);
		painel_menu.add(txtJogador1);
		// Inser????o de jogadores //

		btnLimparHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();

				conexao.limparHistorico(); // Limpa o hist??rico
				conexao.limparTabelaJogadores();
				conexao.fecharConexao(); // Fecha a conex??o com o banco de dados
			}
		});

		btVoltarTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});

		btVoltarTela_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});

		btnVerHistrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();

				conexao.atualizarHistorico();
				List<String> historico = conexao.visualizarHistorico();

				// Crie a nova janela ou aba para exibir os dados do hist??rico
				JTextArea areaTexto = new JTextArea();
				for (String linha : historico) {
					areaTexto.append(linha + "\n");
				}
				editorPane.setText(areaTexto.getText());
				conexao.fecharConexao();
				tabbedPane.setSelectedIndex(3);
			}
		});

		btnNovoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(txtJogador1.getText().isEmpty()) || !(txtJogador2.getText().isEmpty())
						|| !(txtJogador3.getText().isEmpty())) {

					// Cria uma inst??ncia da classe conexaoBD
					conexaoBD conexao = new conexaoBD();
					conexao.criarTabela();
					conexao.criarTabelaPartida();

					if (!(txtJogador1.getText().isEmpty())) {
						Jogador jogador1 = new Jogador(txtJogador1.getText());
						jogadores.add(jogador1);
						conexao.inserirJogador(jogador1.getNome());
					}

					if (!(txtJogador2.getText().isEmpty())) {
						Jogador jogador2 = new Jogador(txtJogador2.getText());
						jogadores.add(jogador2);
						conexao.inserirJogador(jogador2.getNome());
					}

					if (!(txtJogador3.getText().isEmpty())) {
						Jogador jogador3 = new Jogador(txtJogador3.getText());
						jogadores.add(jogador3);
						conexao.inserirJogador(jogador3.getNome());
					}
					// Obt??m os valores dos campos de texto
					tabbedPane.setSelectedIndex(1);
					rdBtManoelGomes.setSelected(true);

					txtJogador1.setText("");
					txtJogador2.setText("");
					txtJogador3.setText("");
					conexao.fecharConexao();
					partida.resetPartida(jogadores, 1);

					lbJogadorAtual.setText(jogadores.get(0).getNome());

					painel_cronometro.start();
				} else {
					JOptionPane.showMessageDialog(null,
							"N??o h?? jogadores escalados para a partida, favor inserir o(s) nome(s)!");
				}
			}
		});

		rdBtOrochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtOrochi.isSelected()) {
					painel_tabuleiro.switchImage(2);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 2 + ".png").getImage()
							.getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});

		rdBtManoelGomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtManoelGomes.isSelected()) {
					painel_tabuleiro.switchImage(1);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 1 + ".png").getImage()
							.getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});

		rdBtFenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtFenda.isSelected()) {
					painel_tabuleiro.switchImage(3);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 3 + ".png").getImage()
							.getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});

		btTrocarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdBtManoelGomes.setVisible(true);
				rdBtOrochi.setVisible(true);
				rdBtFenda.setVisible(true);
				lbSelecioneImg.setVisible(true);
				btTrocarFoto.setVisible(false);
			}
		});

		btSalvarJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();

				int[] arrayBoard = painel_tabuleiro.boardToArray();
				// Salva o tabuleiro no banco de dados
				conexao.salvarPartida(arrayBoard, jogadores, painel_cronometro.getElapsedTime(),
						Integer.parseInt(painel_tabuleiro.image_selected), partida.getId(), partida.getCurrentPlayer()); // chama
																															// o
																															// m??todo
				// salvarTabuleiro
				// da classe
				JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso!");
			}
		});

		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel_cronometro.stop();
				tabbedPane.setSelectedIndex(0);
				jogadores.clear();
				partida.setCurrentPlayer(0);
			}
		});

		btContinuarJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				tabbedPane.setSelectedIndex(2);
				conexaoBD conexao = new conexaoBD();

				for (int i = 0; i < conexao.listarPartidas().size(); i++) {
					JLabel lbPartida = new JLabel();
					lbPartida.setBounds(410, 71 + i * 20, 217, 23);
					lbPartida.setVisible(true);
					lbPartida.setText(conexao.listarPartidas().get(i));
					partidas.add(lbPartida);

					JRadioButton rdbtnNewRadioButton = new JRadioButton(conexao.listarPartidas().get(i).split(",")[0]);
					buttonGroup_1.add(rdbtnNewRadioButton);
					rdbtnNewRadioButton.setBounds(373, 71 + i * 20, 50, 23);
					btsPartidas.add(rdbtnNewRadioButton);

				}

				for (int i = 0; i < partidas.size(); i++) {
					painel_carregar_jogo.add(btsPartidas.get(i));
					painel_carregar_jogo.add(partidas.get(i));
				}
				conexao.fecharConexao();


			}
		});

		// Bot??o de Novo jogo (Iniciador da partida)
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conexaoBD conexao = new conexaoBD();

				int partidaSelected = 0;

				for (int i = 0; i < btsPartidas.size(); i++) {
					if (btsPartidas.get(i).isSelected()) {
						partidaSelected = Integer.parseInt(btsPartidas.get(i).getText());
					}
				}
				
				int[][] board = conexao.carregarPartida(partidaSelected);
				painel_tabuleiro.setBoard(board);
				jogadores.clear();
				int num_jogador = conexao.carregarJogadorAtual(partidaSelected);
				partida.setCurrentPlayer(num_jogador);
				jogadores.addAll(conexao.carregarJogadoresTabuleiro(partidaSelected));
				lbJogadorAtual.setText(jogadores.get(partida.getCurrentPlayer()).getNome());
				painel_cronometro.setElapsedTime(conexao.carregarCronometro(partidaSelected));
				int selected_image = conexao.carregarImagemTabuleiro(partidaSelected);
				painel_tabuleiro.switchImage(selected_image);
				ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + selected_image + ".png").getImage()
						.getScaledInstance(190, 190, Image.SCALE_DEFAULT));
				lbImgCompleta.setIcon(icon);
				

				if (selected_image == 1) {
					rdBtManoelGomes.setSelected(true);
				}else if (selected_image == 2) {
					rdBtOrochi.setSelected(true);
				}else {
					rdBtFenda.setSelected(true);
				}

				tabbedPane.setSelectedIndex(1);
				painel_cronometro.start();

				conexao.fecharConexao();
			}
		});
		
		// Bot??o para passar para o pr??ximo jogador
		btNextPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexaoBD conexao = new conexaoBD();
					conexao.inserirDados(jogadores.get(partida.getCurrentPlayer()).getNome(),
							partida.calcularPontuacao((painel_cronometro.getElapsedTime()) / 1000));
					lbJogadorAtual.setText(jogadores.get(partida.incrementCurrentPlayer()).getNome());
					painel_cronometro.reset();
					int[][] board = painel_tabuleiro.getTabuleiroInicial();
					painel_tabuleiro.setBoard(board);
					btNextPlayer.setVisible(false);
					conexao.fecharConexao();
				} catch (Exception e2) {
					try {
						conexaoBD conexao = new conexaoBD();
						conexao.inserirDados(jogadores.get(partida.getCurrentPlayer()).getNome(),
								partida.calcularPontuacao((painel_cronometro.getElapsedTime()) / 1000));
						conexao.fecharConexao();
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, "Fim de Jogo, o ??ltimo jogador encerrou sua partida!");
						jogadores.clear();
						partida.setCurrentPlayer(-1);
						tabbedPane.setSelectedIndex(0);
					}

				}
			}
		});

		// Lida com o bot??o de resolver o puzzle automaticamente
		btAutoResolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel_cronometro.stop(); // Para o cronometro
				painel_tabuleiro.solveBoard(); // Resolve o puzzle
				painel_cronometro.setVisibilityStart(false);
				btNextPlayer.setVisible(true); // Deixa poss??vel passar para o pr??ximo jogador
			}
		});
	}
}
