import java.util.HashMap;
import java.util.Scanner;

public class Lagerverwaltung {
    private static HashMap<String, Integer> lagerMap = new HashMap<String, Integer>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        //* Lagertestfüllung */
        lagerMap.put("Hose", 10);
        lagerMap.put("Pullover", 15);
        lagerMap.put("Hemd", 10);
        lagerMap.put("Bluse", 13);
        lagerMap.put("Shirt", 23);

        //* Hauptmenü wird aufgerufen */
        mainMenu();
    }
    //* Eigene print Methode */
    private static void print(String myString) {
        System.out.print(myString);
    }
    //* Methode um Ware hinzuzufügen */
    private static void addWare(String addWhat) throws Exception {
        addWhat = addWhat.replaceAll("\\s", "");
        String[] split = addWhat.split(",");
        String was = split[0];
        int menge = Integer.parseInt(split[1]);
        lagerMap.merge(was, menge, (oldValue, newValue) -> oldValue + newValue);
        showWare();
    }

    //* Methode um Ware zu entfernen */
    private static void remWare(String remWhat) throws Exception {
        remWhat = remWhat.replaceAll("\\s", "");
        String[] split = remWhat.split(",");
        String was = split[0];
        int menge = Integer.parseInt(split[1]);
        lagerMap.merge(was, menge, (oldValue, newValue) -> oldValue - newValue);
        //* Möglicherweise muss hier nochwas gemacht werden wenn es in den - Bereich geht */
        showWare();
    }

    //* Methode zum Anzeigen des Lagerbestandes */
    private static void showWare() throws Exception {
        TerminalExtra.terminalClear();
        lagerMap.forEach((key, value) -> print(key + ": "+ value + "\n"));
        print("---------------------------------------------------\n");
        print("Drücken Sie eine beliebige Taste um fortzufahren...");
        scanner.nextLine();
        mainMenu();
    }

    private static void mainMenu() throws Exception {
        TerminalExtra.terminalClear();

        int userInput;
        String userInputString;

        //* Anzeigen des Lagermenüs */
        print(  "Willkommen im Lagerverwaltungssystem!\n\n" +
                "Bitte wählen sie ihre gewünschte Aktion:\n" +
                "----------------------------------------\n" + 
                "1. Lagerbestand anzeigen\n" + 
                "2. Waren hinzufügen\n" +
                "3. Waren entnehmen\n" +
                "4. Beenden\n" +
                "\n");

        //* Warten auf Benutzereingabe */
        do {
            print("Eingabe: ");
            try {
                userInput = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                userInput = 0;
            }
        } while (userInput > 4 || userInput < 1);

        //* Ausführen der Benutzereingabe */
        switch (userInput) {
            case 1: showWare(); break;
            case 2: print("Welche Ware möchten Sie hinzufügen? (Ware, Menge): \n"); 
                    do {
                        print("Eingabe: ");
                        try {
                            userInputString = scanner.nextLine();
                        } catch (Exception e) {
                            userInputString = "";
                        }
                    } while (userInputString.equals("") && userInputString.contains(",") == false);
                    addWare(userInputString);
                    break;
            case 3: print("Welche Ware möchten Sie entfernen? (Ware, Menge): \n"); 
                    do {
                        print("Eingabe: ");
                        try {
                            userInputString = scanner.nextLine();
                        } catch (Exception e) {
                            userInputString = "";
                        }
                    } while (userInputString.equals("") && userInputString.contains(",") == false);
                    remWare(userInputString);
                    break;
            case 4: scanner.close(); break;
            default:    print("#ERROR bei der Eingabe.\n"); 
                        mainMenu();
        }
    }

    //* Methode die auf die nächste Benutzereingabe wartet */

}

