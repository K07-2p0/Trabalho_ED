package src.model.map;

public class Corridor {
    private final Room roomA;
    private final Room roomB;
    private final double weight;

    public Corridor(Room roomA, Room roomB, double weight) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.weight = weight;
    }
    
    public Room getRoomA() {
        return roomA;
    }

    public Room getRoomB() {
        return roomB;
    }
    
    public double getWeight() {
        return weight;
    }
}