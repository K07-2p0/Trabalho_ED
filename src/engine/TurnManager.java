package src.engine;

import src.model.player.Player;
import Queues_Stacks.CircularArrayQueue;
import Exceptions.EmptyCollectionException;

/**
 * Responsável por gerir a ordem dos jogadores e o ciclo de turnos.
 * Usa a estrutura CircularArrayQueue<Player> da sua API.
 */
public class TurnManager {
    private CircularArrayQueue<Player> playerQueue;

    public TurnManager() {
        this.playerQueue = new CircularArrayQueue<>();
    }

    /**
     * Adiciona um jogador ao ciclo de turnos.
     */
    public void addPlayer(Player player) {
        playerQueue.enqueue(player);
    }

    /**
     * Remove o jogador atual e coloca-o no fim da fila para o próximo turno.
     * @return O próximo jogador ativo.
     * @throws EmptyCollectionException Se não houver jogadores.
     */
    public Player getNextPlayer() throws EmptyCollectionException {
        if (playerQueue.isEmpty()) {
            throw new EmptyCollectionException("Não há jogadores para gerir.");
        }
        
        Player activePlayer = playerQueue.dequeue();
        
        // Coloca o jogador no fim da fila para o próximo ciclo, mantendo a ordem.
        playerQueue.enqueue(activePlayer); 
        
        return activePlayer;
    }
    
    // --- Métodos de Utilidade ---
    
    /**
     * Retorna o jogador que está no topo da fila (seria o próximo a jogar) sem o remover.
     */
    public Player peekNextPlayer() throws EmptyCollectionException {
        return playerQueue.first();
    }
    
    public int size() {
        return playerQueue.size();
    }
}
