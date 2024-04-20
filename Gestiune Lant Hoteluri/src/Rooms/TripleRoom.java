package Rooms;

public class TripleRoom extends Room
{
    public TripleRoom(int roomNumber, int floor) {
        super(roomNumber, floor);
    }

    public TripleRoom(TripleRoom room) {
        super(room);
    }

    @Override
    public double getPrice() {
        return 3.0 * pricePerPerson;
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
