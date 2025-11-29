package src.model.player;

import src.ai.Strategy;
import src.model.map.Room;
import java.util.Random;

/**
 * Representa um jogador Bot (controlado pela AI) no Labirinto da Glória.
 * Os bots tomam decisões de movimento com base numa estratégia definida.
 */
public class BotPlayer extends Player {

    // A estratégia que o bot usará para tomar decisões
    private final Strategy strategy;

    /**
     * Construtor para a classe BotPlayer.
     *
     * @param id O identificador único do bot.
     * @param name O nome do bot.
     * @param initialRoom A sala onde o bot começa o jogo.
     * @param strategy A estratégia de AI a ser usada (e.g., RandomStrategy, SimpleHeuristicStrategy).
     */
    public BotPlayer(String id, String name, Room initialRoom, Strategy strategy) {
        super(id, name, initialRoom);
        this.strategy = strategy;
    }

    /**
     * Retorna a estratégia de AI configurada para este bot.
     * @return A instância da estratégia.
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Implementa a lógica de escolha do próximo movimento para o bot,
     * delegando a decisão à sua estratégia de AI.
     *
     * Nota: O parâmetro {@code availableRooms} é tipado como Object para refletir
     * o teu requisito de usar uma estrutura de dados personalizada (API das aulas).
     *
     * @param availableRooms Uma coleção da tua API com as salas adjacentes disponíveis.
     * @return A sala escolhida para o próximo movimento.
     */
    @Override
    public Room chooseNextMove(Object availableRooms) {
        // Se a tua lista personalizada for convertível para uma lista de Java ou
        // se a tua estratégia puder aceitar a tua estrutura de dados diretamente.
        // Assumindo por enquanto que a estratégia trata da estrutura de dados.
        // O método real precisará de ser ajustado dependendo da assinatura do método
        // de estratégia e da tua estrutura de dados.

        // Simulação básica de como a estratégia seria chamada:
        // return this.strategy.determineMove(this.getCurrentRoom(), availableRooms);

        // *** IMPLEMENTAÇÃO PROVISÓRIA APENAS PARA COMPILAR ***
        // No contexto deste projeto, terás que garantir que a tua estrutura de dados
        // (que é o Object availableRooms) é manipulada corretamente pela Strategy.

        if (availableRooms == null) {
            System.err.println("BotPlayer: Nenhuma sala disponível para movimento.");
            return this.getCurrentRoom(); // Permanece na sala atual
        }

        // Se availableRooms for uma lista do teu API, a estratégia deve
        // iterar sobre ela para escolher a melhor Room.
        // Como não tenho a tua API, farei uma escolha aleatória *se* a estrutura for
        // facilmente convertível em algo iterável, como uma List<Room>.
        try {
            @SuppressWarnings("unchecked")
            java.util.List<Room> rooms = (java.util.List<Room>) availableRooms;
            if (rooms.isEmpty()) {
                return this.getCurrentRoom();
            }
            Random random = new Random();
            return rooms.get(random.nextInt(rooms.size()));
        } catch (ClassCastException e) {
            // Se a estrutura não for uma List<Room> de Java, terás que implementar
            // a lógica de escolha dentro da tua estratégia (Strategy.java)
            // que sabe lidar com a tua estrutura de dados personalizada.
            System.err.println("BotPlayer: A estratégia precisa de ser implementada para lidar com a estrutura de dados personalizada.");
            return this.getCurrentRoom();
        }
        // *** FIM DA IMPLEMENTAÇÃO PROVISÓRIA ***
    }
}