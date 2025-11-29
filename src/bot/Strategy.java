package src.bot;

import src.model.map.Room;

/**
 * Interface que define o comportamento de uma estratégia de AI para jogadores Bot.
 * As diferentes classes que implementarem esta interface (e.g., RandomStrategy, SimpleHeuristicStrategy)
 * vão ditar como o bot escolhe o seu próximo movimento.
 */
public interface Strategy {

    /**
     * Determina a próxima sala para onde o bot deve se mover.
     *
     * @param currentRoom A sala atual onde o bot se encontra.
     * @param availableRooms A lista (ou estrutura de dados personalizada) de salas adjacentes.
     * @return A sala escolhida para o próximo movimento.
     */
    Room determineMove(Room currentRoom, Object availableRooms);
}