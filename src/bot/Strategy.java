package src.bot;

import src.model.map.Room;

public interface Strategy {

    Room determineMove(Room currentRoom, Object availableRooms);
}