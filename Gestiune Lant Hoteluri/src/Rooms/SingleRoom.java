package Rooms;

public class SingleRoom extends Room
{
    public SingleRoom(int roomNumber, int floor) {
        super(roomNumber, floor);
    }

    public SingleRoom(SingleRoom room) {
        super(room);
    }

    @Override
    public double getPrice() {
        return pricePerPerson;
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
