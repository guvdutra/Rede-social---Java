/**
 * Interface que representa uma publicação somente-leitura do Feed de Notícias da Rede Social
 * 
 * @author  Julio Cesar Alves
 */
public interface Publicacao {
    // Retorna o id da mensagem
    int getId();
    
    // Retorna o autor da mensagem
    String getAutor();

    // Retorna a mensagem como uma string formatada
    String getTextoExibicao();
}
