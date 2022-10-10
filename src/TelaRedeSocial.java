
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Font;

/**
 * Classe criada para implementar a interface gráfica da Rede Social.
 * O objetivo dessa implementação é didático! 
 * - Exercitar e apresentar conceitos de GUIs (Interfaces Gráficas de Usuário) 
 *   e Tratamento de Exceções
 * 
 * @author Julio Cesar Alves
 */
public class TelaRedeSocial {
    // Janela da nossa tela
    private JFrame janela;
    // Caixa de texto para exibir o feed de noticiai    
    private JTextArea areaTextoFeed;
    // Botão para postar uma mensagem no feed
    private JButton botaoPostarMensagem;
    // Botão para curtir uma mensagem do feed
    private JButton botaoCurtir;
    // Botão para comentar uma mensagem do feed
    private JButton botaoComentar;
    // Botão para atualizar o feed
    private JButton botaoAtualizar;
    // Botão para buscar mensagens de um determinado autor
    private JComboBox<String> botaoListarAutores;
    // atributo booleano para segurar a atualização 
    private boolean liberarAtualizacao;

    // Botão menu
    private JMenuItem itemMenuPostarMensagem;
    private JMenuItem itemMenuCurtir;
    private JMenuItem itemMenuComentar;
    private JMenuItem itemMenuSair;

    
    // Objeto que representa a Regra de Negócios (a lógica da Rede Social em si)
    private FeedNoticias feed;
    
    /**
     * Construtor da classe: cria o feed, os componentes e monta a tela.
    */
    public TelaRedeSocial() {
        feed = new FeedNoticias();
        
        construirJanela();
        montarMenu();
        tratarEvento();
    }

    /**
     * Constroi os componentes e monta a janela
    */
    private void construirJanela() throws HeadlessException {
        janela = new JFrame("GUI - Rede Social");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        criarComponentes();
        
        montarJanela();
    }

    /**
     * Cria os componentes da tela e faz a inscrição nos eventos necessários
     */
    private void criarComponentes() {
        // criando os componentes
        areaTextoFeed = new JTextArea();
        areaTextoFeed.setFont(new Font("Serif", Font.ITALIC, 16));
        botaoPostarMensagem = new JButton("Postar Mensagem");
        botaoCurtir = new JButton("Curtir");
        botaoComentar = new JButton("Comentar");
        botaoAtualizar = new JButton("Atualizar");

        botaoListarAutores = new JComboBox<>();
        botaoListarAutores.addItem("Todos");
        
        // impede que o usuário edite a área de texto do feed
        areaTextoFeed.setEditable(false);

        // adiciona o método que tratará o evento de clique no botão Postar Mensagem
        botaoPostarMensagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postarMensagem();
            }            
        });
        
        // adiciona o método que tratará o evento de clique no botão Curtir
        botaoCurtir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curtirMensagem();
            }
        });

        // adiciona o método que tratará o evento de clique no botão Comentar
        botaoComentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comentarMensagem();
            }
        });

        // adiciona o método que tratará o evento de clique no botão Atualizar
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TESTE
                atualizarAreaTextoFeed("Todos");
            }
        });

        // adiciona o método que tratará o evento de clique no botão de seleção de autores
        botaoListarAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(liberarAtualizacao) {
                    int i = botaoListarAutores.getSelectedIndex();
                    String autor = botaoListarAutores.getItemAt(i);
                    atualizarAreaTextoFeed(autor);
                }
            }
        });
    }

    /**
     * Monta a janela
     */
    private void montarJanela() {
        janela.setSize(500, 600);
        
        // Para mais detalhes sobre o BorderLayout acesse:
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
        janela.setLayout(new BorderLayout());

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.add(new JLabel("Feed de Notícias"));
        painelCentral.add(new JScrollPane(areaTextoFeed));
        janela.add(painelCentral, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(botaoPostarMensagem);
        painelBotoes.add(botaoCurtir);
        painelBotoes.add(botaoComentar);
        painelBotoes.add(botaoAtualizar);
        janela.add(painelBotoes, BorderLayout.SOUTH);

        JPanel painelBotoesTop = new JPanel();
        painelBotoesTop.add(new JLabel("Filtrar mensagens do autor: "));
        painelBotoesTop.add(botaoListarAutores);
        janela.add(painelBotoesTop, BorderLayout.NORTH);
    }

    public void tratarEvento() {

        // Clique do mouse
        JPopupMenu popup = new JPopupMenu();
        JMenuItem popupAtualizar = new JMenuItem("Atualizar");
        JMenuItem popupLimpar = new JMenuItem("Limpar");
        popup.add(popupAtualizar);
        popup.add(popupLimpar);

        areaTextoFeed.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popup.show(areaTextoFeed, e.getX(), e.getY());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // adiciona o método que tratará o evento de clique no botão Atualizar
        popupAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAreaTextoFeed("Todos");
            }
        });

        // adiciona o método que tratará o evento de clique no botão Atualizar
        popupLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaTextoFeed.setText("");
            }
        });

        
    }
    
    /*
     * Exibe a tela da Rede Social
    */
    public void exibir() {
        janela.setVisible(true);
    }

    // Monta o menu
    private void montarMenu() {
        // cria a barra de menu, o menu e o item de menu
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuOpcoes = new JMenu("Opções");

        itemMenuPostarMensagem = new JMenuItem("Postar Mensagem");
        itemMenuCurtir = new JMenuItem("Curtir");
        itemMenuComentar = new JMenuItem("Comentar");
        itemMenuSair = new JMenuItem("Sair");

        // adiciona o item de menu ao menu, e o menu à barra
        menuOpcoes.add(itemMenuPostarMensagem);
        menuOpcoes.add(itemMenuCurtir);
        menuOpcoes.add(itemMenuComentar);
        menuOpcoes.add(itemMenuSair);
        barraMenu.add(menuOpcoes);

        // define a barra de menu para a janela
        janela.setJMenuBar(barraMenu);

        // se inscreve no evento de clique no item de menu
        itemMenuPostarMensagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postarMensagem();
            }            
        });

        // adiciona o método que tratará o evento de clique no botão Curtir
        itemMenuCurtir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curtirMensagem();
            }
        });

        // adiciona o método que tratará o evento de clique no botão Comentar
        itemMenuComentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comentarMensagem();
            }
        });

        // adiciona o método que sai
        itemMenuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        
    }
    
    /**
     * Posta uma mensagem no feed. Solicita o autor e a mensagem ao usuário,
     * posta no Feed e atualiza a área de texto de exibição do feed.
     */
    private void postarMensagem() {
        String autor = JOptionPane.showInputDialog("Autor da mensagem");
        String mensagem = JOptionPane.showInputDialog("Texto da mensagem");        
        feed.postarMensagemTexto(autor, mensagem);
        tratarBotaoListar();
        atualizarAreaTextoFeed("Todos");
    }
    
    /**
     * Curte uma mensagem. Solicita o identificador da mensagem ao usuário,
     * curte a mensagem e atualiza a área de texto de exibição do feed.
     */
    private void curtirMensagem() {
        int idMensagem = Integer.parseInt(JOptionPane.showInputDialog("Id da mensagem"));
        feed.curtir(idMensagem);
        atualizarAreaTextoFeed("Todos");
    }

    /**
     * Comenta uma mensagem. Solicita o identificador da mensagem ao usuário,
     * comenta a mensagem e atualiza a área de texto de exibição do feed.
     */
    private void comentarMensagem() {
        int idMensagem = Integer.parseInt(JOptionPane.showInputDialog("Id da mensagem"));
        String mensagem = JOptionPane.showInputDialog("Texto do comentário");
        feed.comentar(idMensagem, mensagem);
        atualizarAreaTextoFeed("Todos");
    }

    /**
     * Atauliza a área de texto de exibição do Feed.
     */
    private void atualizarAreaTextoFeed(String autor) { 
        // liberarAtualizacao = false;
        if(autor.equals("Todos")) {
            // Limpa a lista de publicações
            areaTextoFeed.setText("");

            // Obtém as publicações do feed de notícias
            List<Publicacao> publicacoes = feed.getPublicacoes();

            // Percorre a lista de publicações adicionando na área de texto o texto da publicação
            for (Publicacao publicacao : publicacoes) {
                areaTextoFeed.append(publicacao.getTextoExibicao());
            }

        } else {
            // Limpa a lista de publicações
            areaTextoFeed.setText("");

            // Obtém as publicações do feed de notícias
            List<Publicacao> publicacoes = feed.getPublicacoes(autor);

            // Percorre a lista de publicações adicionando na área de texto o texto da publicação
            for (Publicacao publicacao : publicacoes)
                areaTextoFeed.append(publicacao.getTextoExibicao());
        }
    }

    // trata o clique no espaço listar
    private void tratarBotaoListar() {
        List<String> listaAutores = feed.retornarAutores();

        // nao atualiza o feed enquanto true
        liberarAtualizacao = false;

        botaoListarAutores.removeAllItems();
        botaoListarAutores.addItem("Todos");

        for(String elem : listaAutores)
            botaoListarAutores.addItem(elem);
        
        // pode atualizar o feed
        liberarAtualizacao = true;
    }

}
