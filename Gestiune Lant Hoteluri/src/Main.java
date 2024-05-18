import Buildings.*;
import Facilities.Facility;
import Rooms.*;

import Services.DatabaseGetter;
import Services.Setup;

import java.sql.*;

import Locations.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Setup.get();

        Facility facility = new Facility(1, "description");
        try {
            facility.create();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        Location location = new Location(2, "locatie");
        try {
            location.create();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        Building building = new Building(1, 1900, 1);
        building.create();

        Hotel hotel = new Hotel(1, "hotel", 1);
        hotel.create();



        Room room = new Room(4, 1, 1, 1, 1.0);
        try {
            room.create();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        SingleRoom singleRoom = new SingleRoom(1);
        try {
            singleRoom.create();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }
}

/*
TODO: posibile query-uri
TOATE DELETURILE VOR FI IN CASCADA!!!
1. sa se afiseze toate hotelurile dintr-o locatie data cu mai mult de nr stele date
2. sa se mareasca salariul tuturor angajatilor dintr-un hotel dat cu un anumit procent
3. sa se concedieze toti angajati dintr-un anumit hotel care au salariul peste o valoare data
4. sa se afiseze toti angajatii din toate hotelurile in ordine descrescatoare dupa salariu (union, ca un angajat poate lucra in mai multe locuri)
5. sa se adauge un nou angajat la o locatie si un hotel specificat
6. sa se adauge un nou hotel la o locatie specificata
7. sa se adauge o locatie specificata
8. sa se afiseze toti angajatii cu peste x salariu sortati crescator dupa primul prenume, descrescator dupa al doilea, crescator dupa ultimul
9. sa se stearga un angajat
10. sa se stearga un hotel (stergere in cascada)
11. sa se afiseze pt un hotel dintr-o locatie data numarul de camere single/double/triple pe care le are
12. sa se adauge la un hotel o camera singla
13. sa se adauge la un hotel o camera dubla
14. sa se adauge la un hotel o camera tripla
15. sa se stearga o camera dintr-un hotel
 */