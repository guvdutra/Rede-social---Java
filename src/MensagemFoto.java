/**
 * Subclasse da classe Mensagem que define uma Mensagem que possui foto.
 * Como a Rede Social eh de linha de comando :) na verdade guarda apenas o nome
 * do arquivo da foto e um texto de legenda
 * 
 * Esse eh um exemplo do livro: Programacao Orientada a Objetos com Java - uma 
 * introducao pratica utilizando BlueJ.
 * 
 * @author  Michael Kölling and David J. Barnes
 *          Traduzido e adaptado por Julio Cesar Alves
 */
public class MensagemFoto extends Mensagem {
    // nome do arquivo que contem a foto
    private String arquivoFoto;
    // legenda da foto a ser usada na rede social
    private String legenda;

    /**
     * Cria uma mensagem com foto a partir do nome do autor, nome do arquivo
     * da foto e legenda da foto.
     * 
     * @param autor Nome do autor da mensagem
     * @param arquivoFoto Nome do arquivo da foto
     * @param legenda Legenda da foto
     */
    public MensagemFoto(String autor, String arquivoFoto, String legenda) {
        super(autor);
        this.arquivoFoto = arquivoFoto;
        this.legenda = legenda;
    }

    @Override
    /**
     * Retorna o conteúdo da mensagem (nesse caso o nome e a legenda da foto)
     * 
     * @return O conteúdo da mensagem
    */
    protected String getConteudoTextoExibicao() {
        return arquivoFoto + "\n" + legenda;
    }
}