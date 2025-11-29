package src.model.player;

import src.model.map.Room;


public abstract class Player {

    // ID do jogador
    private final String id;
    // Nome do jogador
    private final String name;
    // Posição do jogador
    private Room currentRoom;
    // Número de turnos que o jogador fica impedido de jogar
    private int turnsBlocked;

    /**
     * Construtor para a classe Player.
     *
     * @param id O identificador único do jogador.
     * @param name O nome do jogador.
     * @param initialRoom A sala onde o jogador começa o jogo.
     */
    public Player(String id, String name, Room initialRoom) {
        this.id = id;
        this.name = name;
        this.currentRoom = initialRoom;
        this.turnsBlocked = 0;
    }

    /**
     * Retorna o ID único do jogador.
     * @return O ID do jogador.
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna o nome do jogador.
     * @return O nome.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna a sala onde o jogador está atualmente.
     * @return A sala atual.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Define a nova sala onde o jogador se move.
     * @param newRoom A nova sala.
     */
    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    /**
     * Verifica se o jogador está atualmente bloqueado (impedido de jogar).
     * @return true se o jogador estiver bloqueado, false caso contrário.
     */
    public boolean isBlocked() {
        return turnsBlocked > 0;
    }

    /**
     * Bloqueia o jogador por um determinado número de turnos.
     * @param turns O número de turnos a bloquear.
     */
    public void block(int turns) {
        this.turnsBlocked = turns;
    }

    /**
     * Reduz em um o número de turnos bloqueados, se houver.
     */
    public void reduceBlockTurns() {
        if (turnsBlocked > 0) {
            turnsBlocked--;
        }
    }

    /**
     * Método abstrato que representa a lógica para o jogador escolher o seu próximo movimento.
     * A implementação será diferente para um jogador manual (que espera input) e um bot (que calcula a jogada).
     *
     * @param availableRooms A lista de salas adjacentes disponíveis para movimento (o tipo exato
     * dependerá da tua API de estruturas de dados e de como {@code MapGraph} a usa).
     * @return A sala escolhida para o próximo movimento.
     */
    public abstract Room chooseNextMove(Object availableRooms);

    /**
     * Retorna uma representação em string do jogador para fins de depuração ou exibição.
     * @return Uma string formatada.
     */
    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentRoomId=" + currentRoom.getId() +
                ", turnsBlocked=" + turnsBlocked +
                '}';
    }
}