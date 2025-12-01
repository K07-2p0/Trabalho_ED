package model.Player;

import model.map.Room;

public abstract class Player {

    // ID do jogador
    private final String id;
    // Nome a ser exibido
    private final String name;
    // Posição atual do jogador no mapa (uma Sala)
    private Room currentRoom;
    // Número de turnos que o jogador fica impedido de jogar
    private int turnsBlocked;


    public Player(String id, String name, Room initialRoom) {
        this.id = id;
        this.name = name;
        this.currentRoom = initialRoom;
        this.turnsBlocked = 0;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }


    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }


    public boolean isBlocked() {
        return turnsBlocked > 0;
    }


    public void block(int turns) {
        this.turnsBlocked = turns;
    }


    public void reduceBlockTurns() {
        if (turnsBlocked > 0) {
            turnsBlocked--;
        }
    }


    public abstract Room chooseNextMove(Object availableRooms);

    /*
    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentRoomId=" + currentRoom.getId() +
                ", turnsBlocked=" + turnsBlocked +
                '}';
    } */
}
