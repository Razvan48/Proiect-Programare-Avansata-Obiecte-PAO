package Rooms;

import Services.Database;

public class SingleRoom extends Room
{
    private int singleRoomID;

    public SingleRoom(int singleRoomID) {
        super(Database.get().getRoom(singleRoomID));

        this.singleRoomID = singleRoomID;
    }

    public SingleRoom(SingleRoom singleRoom) {
        super(singleRoom);

        this.singleRoomID = singleRoom.singleRoomID;
    }

    @Override
    public String toString() {
        return "Single " + super.toString();
    }

    @Override
    public SingleRoom clone() {
        return new SingleRoom(this);
    }
}
