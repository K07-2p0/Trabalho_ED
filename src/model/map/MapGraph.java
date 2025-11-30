package src.model.map;

import Graphs.Network;
import Lists.ListADT;
import Lists.DoublyLinkedList;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;


public class MapGraph {

    private Network<Room> network;

    public MapGraph() {
        this.network = new Network<>(); 
    }


    public void addRoom(Room room) {
        network.addVertex(room);
    }
    

    public void addCorridor(Room room1, Room room2, double weight) {
        network.addEdge(room1, room2, weight);
    }
    

    public ListADT<Room> getAvailableMoves(Room currentRoom) {
      
        return new DoublyLinkedList<>(); // Implementação temporária
    }
    

    public Iterator<Room> getShortestPath(Room startRoom, Room targetRoom) throws EmptyCollectionException {
        
        return network.iteratorShortestPath(startRoom, targetRoom);
    }
    
 
    public double getShortestPathWeight(Room startRoom, Room targetRoom) {
        return network.shortestPathWeight(startRoom, targetRoom);
    }

}
