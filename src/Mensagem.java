import java.util.*;

/**
 * Classe que representa uma mensagem em uma rede social.
 * 
 * Uma mensagem possui um autor, hora da postagem, número de curtidas e 
 * comentários. O conteudo da mensagem em si deve ser tratado em subclasses.
 * 
 * Esse eh um exemplo do livro: Programacao Orientada a Objetos com Java - uma 
 * introducao pratica utilizando BlueJ.
 * 
 * @author  Michael Kölling and David J. Barnes
 *          Traduzido e adaptado por Julio Cesar Alves
 */
public abstract class Mensagem implements Publicacao  {

    // Identificador da mensagem
    private int id;
    // Atributo estático para controlar os IDs das mensagens
    private static int proximoId = 1;
    // Nome do autor da mensagem
    private String autor;
    // Quantidade de curtidas que a mensagem recebeu
    private int nroCurtidas;    
    // Hora na qual a mensagem foi postada (em milissegundos)
    private long horaPostagem;
    // Lista de comentários que a mensagem recebeu
    private ArrayList<String> comentarios;        

    /**
     * Constroi uma mensagem a partir do nome do autor. 
     * Define automaticamente a hora da postagem; inicializa as curtidas com
     * zero e cria a lista de comentarios vazia.
     * 
     * @param autor Nome do autor da mensagem
     */
    public Mensagem(String autor) {
        this.autor = autor;
        this.id = proximoId;
        proximoId++;
        nroCurtidas = 0;
        horaPostagem = System.currentTimeMillis();
        comentarios = new ArrayList<String>();		
    }

    /**
     * Retorna o id da mensagem
     * 
     * @return Id da mensagem
     */
    @Override
    public int getId() {
        return id;
    }    
    
    /**
     * Retorna o autor da mensagem
     */
    @Override
    public String getAutor() {
        return autor;
    }
    
    /**
     * Realiza a ação de curtir uma mensagem. Na pratica apenas conta mais
     * uma curtida.
     */
    public void curtir() {
        nroCurtidas++;
    }

    /**
     * Adiciona um comentario a mensagem, recebendo apenas o texto do 
     * comentario
     * 
     * @param comentario Texto do comentario
     */
    public void comentar(String comentario) {
        comentarios.add(comentario);
    }

    /**
     * Retorna a mensagem formatada para aparecer no Feed de Noticias. Inclui ha 
     * quanto tempo a mensagem foi publicada.
     * 
     * @return A mensagem formada para o Feed
     */
    @Override
    public String getTextoExibicao() {
        String texto = "  [" + id + "]   " + autor + ": " + getConteudoTextoExibicao();
        texto += "\n\t" + getTextoTempo();
        texto += "\n\t" + nroCurtidas + " pessoas curtiram isso!";
        texto += "\n\t" + "Comentários:\n " + getTextoComentarios();
        
        return texto;
    }

    /**
     * Metodo auxiliar usado no metodo getTextoExibicao para que seja exibido ha 
     * quanto tempo a mensagem foi postada.
     * 
     * @return Texto com a informacao formatada
     */
    private String getTextoTempo() {
        long tempoAtual = System.currentTimeMillis();
        long diferenca = (tempoAtual - horaPostagem);

        long diferencaSeg = diferenca/1000;
        long diferencaMin = diferencaSeg/60;

        if (diferencaMin < 1) {
                return "Há " + diferencaSeg + " segundos";
        }
        else {
                return "Há " + diferencaMin + " minutos";
        }
    }

    /**
     * Metodo auxiliar usado pelo metodo getTextoExibicao para obter os 
     * comentarios da mensagem.
     */
    private String getTextoComentarios() {
        String texto = "";
        if (comentarios.isEmpty()) {
            texto += "\tNao ha comentarios...\n";
        }
        else {            
            for (String comentario : comentarios) {	
                texto += "\t" + comentario + "\n";
            }            
        }
        return texto;
    }
    
    /**
     * Retorna o conteúdo da mensagem (a ser sobrescrito nas subclasses)
     * 
     * @return O conteúdo da mensagem
    */
    protected abstract String getConteudoTextoExibicao();
}