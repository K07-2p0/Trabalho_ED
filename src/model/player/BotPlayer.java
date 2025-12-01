package src.model.player;

import src.bot.Strategy;
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

    @Override
    public Room chooseNextMove(Object availableRooms) {


        if (availableRooms == null) {
            System.err.println("BotPlayer: Nenhuma sala disponível para movimento.");
            return this.getCurrentRoom(); // Permanece na sala atual
        }

        try {
            @SuppressWarnings("unchecked")
            java.util.List<Room> rooms = (java.util.List<Room>) availableRooms;
            if (rooms.isEmpty()) {
                return this.getCurrentRoom();
            }
            Random random = new Random();
            return rooms.get(random.nextInt(rooms.size()));
        } catch (ClassCastException e) {

            System.err.println("BotPlayer: A estratégia precisa de ser implementada para lidar com a estrutura de dados personalizada.");
            return this.getCurrentRoom();
        }
    }
}