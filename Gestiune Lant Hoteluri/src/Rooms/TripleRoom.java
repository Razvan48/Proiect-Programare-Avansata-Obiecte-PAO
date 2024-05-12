package Rooms;

import Services.Database;

public class TripleRoom extends Room
{
    private int tripleRoomID;
    public TripleRoom(int tripleRoomID) {
        super(Database.get().getRoom(tripleRoomID));

        this.tripleRoomID = tripleRoomID;
    }

    public TripleRoom(TripleRoom tripleRoom) {
        super(tripleRoom);

        this.tripleRoomID = tripleRoom.tripleRoomID;
    }

    @Override
    public String toString() {
        return "Triple " + super.toString();
    }

    @Override
    public TripleRoom clone() {
        return new TripleRoom(this);
    }
}
