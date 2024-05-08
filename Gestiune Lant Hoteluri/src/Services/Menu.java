package Services;

import Buildings.Hotel;
import Rooms.DoubleRoom;
import Rooms.Room;
import Rooms.SingleRoom;
import Rooms.TripleRoom;

import java.util.Objects;
import java.util.Scanner;

public class Menu {

    private static Menu INSTANCE = null;

    private boolean isRunning;

    private Menu() {
        this.isRunning = true;
    }

    public static Menu get() {
        if (INSTANCE == null) {
            INSTANCE = new Menu();
        }
        return INSTANCE;
    }

    private String displayCommands() {
        StringBuilder result = new StringBuilder("Welcome to hotel manager\n");

        result.append("Commands:\n");
        result.append("add\n");
        result.append("show\n");
        result.append("clear\n");
        result.append("exit\n");

        return result.toString();
    }

    /*
    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println(this.displayCommands());

        while (this.isRunning) {
            String input = scanner.next();

            if (input.equals("add")) {
                System.out.println("hotel or room?\n");
                String decision = scanner.next();

                if (decision.equals("hotel")) {
                    System.out.println("give hotel name, number of stars and construction year\n");
                    Manager.get().addHotel(new Hotel(scanner.next(), scanner.nextInt(), scanner.nextInt()));
                }
                else if (decision.equals("room")) {
                    System.out.println("give hotel ID\n");
                    int hotelID = scanner.nextInt();
                    System.out.println("what type of room? single/double/triple\n");
                    decision = scanner.next();

                    if (decision.equals("single")) {
                        System.out.println("give room number, floor\n");
                        Manager.get().addRoom(hotelID, new SingleRoom(scanner.nextInt(), scanner.nextInt()));
                    }
                    else if (decision.equals("double")) {
                        System.out.println("give room number, floor\n");
                        Manager.get().addRoom(hotelID, new DoubleRoom(scanner.nextInt(), scanner.nextInt()));
                    }
                    else if (decision.equals("triple")) {
                        System.out.println("give room number, floor\n");
                        Manager.get().addRoom(hotelID, new TripleRoom(scanner.nextInt(), scanner.nextInt()));
                    }
                    else {
                        System.out.println("ERROR :: Unknown room type!\n");
                    }
                }
                else {
                    System.out.println("ERROR :: Unknown decision!\n");
                }
            }
            else if (input.equals("show")) {
                System.out.println("hotel or room?\n");
                String decision = scanner.next();

                if (decision.equals("hotel")) {
                    System.out.println("give hotel ID\n");
                    System.out.println(Manager.get().displayHotel(scanner.nextInt()));
                }
                else if (decision.equals("room")) {
                    System.out.println("give hotel ID and room ID\n");
                    System.out.println(Manager.get().displayRoom(scanner.nextInt(), scanner.nextInt()));
                }
                else {
                    System.out.println("ERROR :: Unknown decision!\n");
                }
            }
            else if (input.equals("exit")) {
                this.isRunning = false;
            }
            else if (input.equals("clear")) {
                System.out.flush();
                System.out.println(this.displayCommands());
            }
            else {
                System.out.println("ERROR :: Unknown command!\n");
            }
        }
    }
     */
}
