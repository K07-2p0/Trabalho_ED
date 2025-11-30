package src.model.map;

/**
 * Representa um Corredor/Caminho entre duas salas (arestas no MapGraph).
 * Esta classe é opcional se gerir os eventos diretamente no GameEngine,
 * mas ajuda a estruturar a lógica.
 */
public class Corridor {
    private final Room roomA;
    private final Room roomB;
    private final double weight;

    public Corridor(Room roomA, Room roomB, double weight) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.weight = weight;
    }
    
    // Futuramente, pode adicionar métodos para:
    // public Event checkEvent() { ... } // Retorna um Event.java aleatório
    
    public double getWeight() {
        return weight;
    }
}