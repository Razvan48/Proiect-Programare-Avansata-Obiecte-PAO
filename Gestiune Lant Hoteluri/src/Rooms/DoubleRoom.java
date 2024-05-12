package Rooms;

import Services.Database;

public class DoubleRoom extends Room
{
    private int doubleRoomID;
    public DoubleRoom(int doubleRoomID) {
        super(Database.get().getRoom(doubleRoomID));

        this.doubleRoomID = doubleRoomID;
    }

    public DoubleRoom(DoubleRoom doubleRoom) {
        super(doubleRoom);

        this.doubleRoomID = doubleRoom.doubleRoomID;
    }

    @Override
    public String toString() {
        return "Double " + super.toString();
    }

    @Override
    public DoubleRoom clone() {
        return new DoubleRoom(this);
    }
}
