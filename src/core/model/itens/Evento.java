package core.model.itens;

/**
 * Define os tipos de Eventos Aleatórios que podem ocorrer ao atravessar um Corredor.
 */
public enum Evento {

    /**
     * Efeito: O jogador ganha jogadas extra.
     */
    JOGADA_EXTRA("Ganha 1 jogada extra."),
    
    /**
     * Efeito: Troca de posição com outro jogador à escolha.
     */
    TROCA_POSICAO("Troca de posição com outro jogador à escolha."),
    
    /**
     * Efeito: O jogador é obrigado a recuar um número de casas.
     */
    RECUAR("Recua 1 ou 2 casas."),
    
    /**
     * Efeito: O jogador fica impedido de jogar durante alguns turnos.
     */
    BLOQUEIO_TURNO("Fica impedido de jogar por 1 a 3 turnos."),
    
    /**
     * Efeito: Troca todos os jogadores de posições aleatoriamente.
     */
    TROCA_GLOBAL("Troca a posição de todos os jogadores."),
    
    /**
     * Efeito: Não acontece nada, para adicionar imprevisibilidade.
     */
    NADA("Nada acontece, por enquanto.");

    private final String descricao;

    /**
     * Construtor do Evento.
     * @param descricao A descrição do efeito do evento.
     */
    Evento(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém a descrição detalhada do efeito do evento.
     * @return A descrição do evento.
     */
    public String getDescricao() {
        return descricao;
    }
}