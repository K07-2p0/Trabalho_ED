package core.model.itens;

/**
 * Representa um Enigma que é colocado ao jogador numa SalaEnigma.
 * Guarda a pergunta, as opções de resposta e a resposta correta.
 */
public class Enigma {

    private final int id;
    private final String pergunta;
    // O ficheiro JSON deve ter um conjunto de opções (strings)
    private final String[] opcoesResposta; 
    private final String respostaCorreta;

    /**
     * Construtor para o Enigma.
     *
     * @param id O identificador único do enigma.
     * @param pergunta A questão a ser colocada.
     * @param opcoesResposta Um array de strings com as possíveis respostas (opções A, B, C, etc.).
     * @param respostaCorreta A string que corresponde à resposta correta.
     */
    public Enigma(int id, String pergunta, String[] opcoesResposta, String respostaCorreta) {
        this.id = id;
        this.pergunta = pergunta;
        this.opcoesResposta = opcoesResposta;
        this.respostaCorreta = respostaCorreta;
    }

    /**
     * Verifica se a resposta fornecida pelo jogador é a correta.
     *
     * @param respostaDoJogador A string de resposta fornecida pelo jogador.
     * @return true se a resposta for correta, false caso contrário.
     */
    public boolean verificarResposta(String respostaDoJogador) {
        // Ignorar diferenças de maiúsculas/minúsculas para facilitar a jogabilidade.
        if (respostaDoJogador == null) {
            return false;
        }
        return respostaDoJogador.trim().equalsIgnoreCase(this.respostaCorreta.trim());
    }
    
    // --- Getters ---

    public int getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    /**
     * Retorna as opções de resposta para apresentação na UI.
     * @return Um array de strings com as opções de resposta.
     */
    public String[] getOpcoesResposta() {
        // Retorna uma cópia (se fosse uma lista mutável) ou o próprio array.
        return opcoesResposta; 
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }
}