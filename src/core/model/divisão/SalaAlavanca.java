package core.model.divisao;

import core.model.ator.Jogador;

/**
 * Representa uma Divisão que contém Alavancas que, se ativadas corretamente,
 * podem desbloquear (ou potencialmente bloquear) Corredores no labirinto.
 */
public class SalaAlavanca extends Divisao {

    // Define qual é a "ação" correta para ativar a alavanca (e.g., 'A', 'B', 'C')
    private final char escolhaCorreta; 
    
    // Lista de IDs de Corredores (arestas) que esta alavanca controla.
    // O GestorMapa usará estes IDs para encontrar os Corredores no Grafo e atualizá-los.
    // Usamos um Array/List (da API) para simular esta coleção.
    private final int[] corredoresControladosIds;
    
    private boolean alavancaAtivada;

    /**
     * Construtor para a Sala da Alavanca.
     *
     * @param id O identificador único da divisão.
     * @param nome O nome da divisão.
     * @param descricao A descrição detalhada da sala.
     * @param escolhaCorreta A opção (A, B, C, etc.) que ativa a alavanca.
     * @param corredoresControladosIds Os IDs dos corredores afetados por esta alavanca.
     */
    public SalaAlavanca(int id, String nome, String descricao, char escolhaCorreta, int[] corredoresControladosIds) {
        super(id, nome, descricao);
        this.escolhaCorreta = Character.toUpperCase(escolhaCorreta);
        this.corredoresControladosIds = corredoresControladosIds;
        this.alavancaAtivada = false; // Começa sempre desativada
    }

    /**
     * Implementação do método abstrato da classe Divisao.
     * Quando um jogador entra, deve tentar ativar a alavanca.
     *
     * @param jogador O jogador que acabou de entrar na divisão.
     * @return true se a alavanca já estiver ativada, ou se o jogador a ativar com sucesso.
     * false se o jogador falhar a escolha e ficar bloqueado.
     */
    @Override
    public boolean processarEntrada(Jogador jogador) {
        // Atualiza a posição do jogador
        setJogadorAtual(jogador);
        
        System.out.println(jogador.getNome() + " entrou em " + getNome() + ".");

        if (alavancaAtivada) {
            System.out.println("Alavanca já ativada! A passagem controlada está aberta.");
            return true; // Alavanca já ativada, pode continuar
        }

        // Se não ativada, o jogador deve tentar
        System.out.println("\n--- ALAVANCA ---");
        System.out.println("Existem várias opções de alavanca. Qual escolhes? (e.g., A, B, C)");
        
        // O MotorJogo (ou a UI) irá solicitar a escolha do jogador (humano ou bot)
        
        // Assumimos que o MotorJogo recebe a escolha e a passa para um método de verificação.
        return tentarAtivarAlavanca(jogador);
    }
    
    /**
     * Método auxiliar para tentar a escolha e atualizar o estado do labirinto.
     * Este método seria chamado pelo MotorJogo após o jogador fornecer uma escolha.
     * * @param jogador O jogador que tenta a ativação.
     * @return true se a escolha for correta, false se falhar.
     */
    public boolean tentarAtivarAlavanca(Jogador jogador) {
        // --- SIMULAÇÃO DA ESCOLHA DO JOGADOR ---
        // Na implementação real, a resposta viria da UI/MotorJogo.
        char escolhaDoJogador = simularEscolha(jogador); 

        if (escolhaDoJogador == this.escolhaCorreta) {
            System.out.println(jogador.getNome() + " acertou na escolha! A passagem é desbloqueada.");
            this.alavancaAtivada = true;
            
            // O MotorJogo/GestorMapa deve ser notificado para realmente mudar o grafo.
            // Aqui, devolvemos true para que o MotorJogo saiba que a jogada continua e que a mudança deve ocorrer.
            return true;
        } else {
            System.out.println(jogador.getNome() + " falhou. A alavanca permanece inativa. Tente de novo no futuro.");
            return false;
        }
    }
    
    /**
     * (Apenas para demonstração) Simula a escolha do jogador.
     * @param jogador O jogador que tenta a ativação.
     * @return A escolha simulada.
     */
    private char simularEscolha(Jogador jogador) {
        // Implementação real: Usa o input do JogadorHumano ou a lógica do JogadorBot
        // Por agora, simulamos um acerto aleatório.
        char[] opcoes = {'A', 'B', 'C'};
        // Usa Math.random() para escolher uma opção aleatoriamente.
        int index = (int) (Math.random() * opcoes.length); 
        return opcoes[index];
    }


    /**
     * Atualiza o estado da divisão. 
     * Neste caso, serve como um marcador para o MotorJogo saber que a alavanca deve ser ativada no Grafo.
     */
    @Override
    public void atualizarEstado() {
        // A principal atualização de estado é a alavancaAtivada.
        // Se este método for chamado pelo MotorJogo, é para notificar o sucesso
        // e possivelmente executar a alteração no grafo.
    }

    // --- Getters ---

    public boolean isAlavancaAtivada() {
        return alavancaAtivada;
    }

    /**
     * Retorna os IDs dos corredores que esta alavanca afeta.
     * O GestorMapa utiliza esta informação para atualizar o Grafo.
     * @return Um array de IDs de corredores.
     */
    public int[] getCorredoresControladosIds() {
        return corredoresControladosIds;
    }
}