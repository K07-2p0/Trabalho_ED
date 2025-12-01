package src.bot;

import src.model.map.Room;
import Lists.DoublyLinkedList;
import src.bot.Strategy;
import java.util.Random;
import java.util.Iterator;


public class RandomStrategy implements Strategy {

    public Room determineMove(Room currentRoom, Object availableRooms) {
        // O parâmetro availableRooms é o seu DoublyLinkedList<Room>
        
        try {
            @SuppressWarnings("unchecked")
            DoublyLinkedList<Room> rooms = (DoublyLinkedList<Room>) availableRooms;
            
            if (rooms == null || rooms.isEmpty()) {
                return currentRoom; 
            }
            
            int size = rooms.size();
            Random random = new Random();
            int randomIndex = random.nextInt(size);
            
            // Usar o Iterator da sua DoublyLinkedList para encontrar a sala aleatória
            Iterator<Room> iterator = rooms.iterator();
            Room chosenRoom = currentRoom;
            int count = 0;
            
            while (iterator.hasNext()) {
                Room room = iterator.next();
                if (count == randomIndex) {
                    chosenRoom = room;
                    break;
                }
                count++;
            }
            
            return chosenRoom;
            
        } catch (ClassCastException e) {
            System.err.println("Erro: A estrutura de dados para o movimento do bot não é DoublyLinkedList.");
            return currentRoom;
        }
    }
}