import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;

public class TerminalExtra {
    public static void terminalClear() {
        if (System.getProperty("os.name").equals("Windows 11")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException e) {
                System.err.println("Ein I/O-Fehler ist aufgetreten: " + e.getMessage());
            } catch (InterruptedException e) {
                System.err.println("Die Ausführung wurde unterbrochen: " + e.getMessage());
            }
        }
    }
}

