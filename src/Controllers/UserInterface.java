package Controllers;

import java.io.File;
import java.util.Scanner;

/**
 * Created by TOAN on 12/27/2014.
 */
public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Host host = new Host();

    public String validString(String stringIn) {
        if (stringIn.isEmpty()) {
            //
        } else {
            stringIn = stringIn.replaceAll("\\s+", " ");
            stringIn = stringIn.replaceAll("(^\\s+|\\s+$)", "");
        }
        return stringIn;
    }

    public void drawMenu() {
        line();
        System.out.format("%-4s%-14s%-23s%-24s|\n", "|STT", "| User name ", "| Full name", "| Email address");
        line();
    }

    public void line() {
        System.out.format("%-4s%-11s%-18s%-21s\n", "+---", "+-------------+", "----------------------+", "-----------------------+");
    }

    public void searchMenu() {
        while (true) {
            System.out.println("== SEARCH ======================\n");
            System.out.println("1. Search by User");
            System.out.println("2. Search by Full Name");
            System.out.println("3. Search by Domain");
            System.out.println("4. Main menu\n");
            System.out.print("Choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                //
            }

            switch (choice) {
                case 1:
                    host.clsDisplay();
                    System.out.print("Search User: ");
                    String user = scanner.nextLine();
                    line();
                    host.smartSearch(validString(user.toLowerCase()), "null", "null",false,false,false);
                    System.out.print("Press enter to continue...\n");
                    String nextStep = scanner.nextLine();
                    if (nextStep != null) {
                        host.clsDisplay();
                        break;
                    }
                    break;
                case 2:
                    host.clsDisplay();
                    System.out.print("Search Name: ");
                    String name = scanner.nextLine();
                    line();
                    host.smartSearch("null", validString(name.toLowerCase()), "null", false, false,false);
                    System.out.print("Press enter to continue...\n");
                    nextStep = scanner.nextLine();
                    if (nextStep != null) {
                        host.clsDisplay();
                        break;
                    }
                    break;
                case 3:
                    host.clsDisplay();
                    System.out.print("Search Domain: ");
                    String domain = scanner.nextLine();
                    line();
                    host.smartSearch("null", "null", validString(domain.toLowerCase()), false, false,false);
                    System.out.print("Press enter to continue...\n");
                    nextStep = scanner.nextLine();
                    if (nextStep != null) {
                        host.clsDisplay();
                        break;
                    }
                    break;
                case 4:
                    host.clsDisplay();
                    this.startMain();
                    break;
            }
        }
    }

    public void exportImportMenu() {
        while (true) {
            System.out.println("== EXPORT, IMPORT TOOLS =========\n");
            System.out.println("1. Export");
            System.out.println("2. Import");
            System.out.println("3. Main Menu");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                //
            }

            switch (choice) {
                case 1:
                    host.clsDisplay();
                    System.out.println("== EXPORT USER TO FILE CSV");
                    System.out.print("\nUser name: ");
                    String userName = scanner.nextLine();
                    line();
                    host.smartSearch(validString(userName.toLowerCase()), "null", "null", false, false, true);
                    break;
                case 2:
                    host.clsDisplay();
                    System.out.println("== IMPORT FILE TO DATABASE");
                    System.out.print("\nSaid the file you want to add database: ");
                    String fileName = scanner.nextLine();
                    FileStream.importCSV(new File(fileName + ".csv"));
                    break;
                case 3:
                    host.clsDisplay();
                    startMain();
                    break;
            }
        }
    }

    public void updateMenu(){
        while (true){
            System.out.println("== UPDATE ======================\n");
            System.out.println("1. Edit");
            System.out.println("2. Delete");
            System.out.println("3. Main Menu\n");
            System.out.print("Choice: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                //
            }

            switch (choice){
                case 1:
                    host.clsDisplay();
                    System.out.print("Search user wants to edit: ");
                    String userEdit = scanner.nextLine();
                    line();
                    host.smartSearch(validString(userEdit.toLowerCase()),"null","null",false,true,false);
                    break;
                case 2:
                    host.clsDisplay();
                    System.out.print("Search user wants to delete: ");
                    String userDel = scanner.nextLine();
                    line();
                    host.smartSearch(validString(userDel.toLowerCase()),"null","null",true,false,false);
                    break;
                case 3:
                    host.clsDisplay();
                    startMain();
                    break;
            }
        }
    }

    public void startMain() {
        while (true) {
            System.out.println("== MENU ======================\n");
            System.out.println("1. Add Person");
            System.out.println("2. Search Option");
            System.out.println("3. Update Email");
            System.out.println("4. Export / Import CSV File");
            System.out.println("5. Display");
            System.out.println("6. Exit\n");
            System.out.print("Choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                //
            }

            switch (choice) {
                case 1:
                    host.addPerson();
                    break;
                case 2:
                    host.clsDisplay();
                    searchMenu();
                    break;
                case 3:
                    host.clsDisplay();
                    updateMenu();
                    break;
                case 4:
                    host.clsDisplay();
                    exportImportMenu();
                    break;
                case 5:
                    host.clsDisplay();
                    drawMenu();
                    host.displayPerson();
                    line();
                    System.out.print("\nPress enter to continue...\n");
                    String nextStep = scanner.nextLine();
                    if (nextStep != null) {
                        host.clsDisplay();
                        break;
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }
}