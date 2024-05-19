import Services.Setup;
import Services.MainMenu;

public class Main {
    public static void main(String[] args) {
        try {
            Setup.get();
            MainMenu.get().run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
