package Rooms;

public class DoubleRoom extends Room
{
    public DoubleRoom(int roomNumber, int floor) {
        super(roomNumber, floor);
    }

    public DoubleRoom(DoubleRoom room) {
        super(room);
    }

    @Override
    public double getPrice() {
        return 2.0 * pricePerPerson;
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
