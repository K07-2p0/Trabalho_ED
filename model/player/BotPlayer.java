package src.model.player;

import src.ai.Strategy;
import src.model.map.Room;
import java.util.Random;


public class BotPlayer extends Player {

    private final Strategy strategy;

    public BotPlayer(String id, String name, Room initialRoom, Strategy strategy) {
        super(id, name, initialRoom);
        this.strategy = strategy;
    }


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
        // *** FIM DA IMPLEMENTAÇÃO PROVISÓRIA ***
    }
}