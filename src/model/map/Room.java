package src.model.map;

public class Room {
    private final String id;
    private final String name;
    private final RoomType type;
    private boolean isLocked; // Controla o bloqueio por Alavanca/Enigma [cite: 37, 39]


    public enum RoomType {
        ENTRY,      // Pontos de entrada 
        CENTRAL,    // Sala do Tesouro (vitória) 
        LEVER,      // Divisão com Alavanca 
        RIDDLE,     // Divisão com Enigma 
        NORMAL
    }


    public Room(String id, String name, RoomType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        
        // Salas com alavancas/enigmas começam bloqueadas
        this.isLocked = (type == RoomType.LEVER || type == RoomType.RIDDLE); 
    }

    // --- Getters e Setters (Outros métodos omitidos para brevidade) ---

    public String getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock() {
        this.isLocked = false;
    }
    
    @Override
    public boolean equals(Object o) {
        // A comparação é feita apenas pelo ID
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }
}