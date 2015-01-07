package Controllers;

import Models.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by TOAN on 12/27/2014.
 */
public class Host {
    ArrayList<Person> pList = new ArrayList<Person>();
    Scanner scanner = new Scanner(System.in);
    EmailValid checkMail = new EmailValid("\\w+@\\w+(\\.\\w+)+");

    public void clsDisplay() {
        for (int i = 0; i < 30; i++) {
            System.out.println("\n");
        }
    }

    public void addPerson() {
        clsDisplay();
        String user, fullName, email;
        while (true) {
            System.out.print("User name: ");
            user = scanner.nextLine();
            if (user.length() > 0) {
                break;
            } else {
                System.out.println("\nYou have entered incorrect, please re-enter your user name\n");
            }
        }

        while (true) {
            System.out.print("Full name: ");
            fullName = scanner.nextLine();
            if (fullName.length() > 0) {
                break;
            } else {
                System.out.println("\nYou have entered incorrect, please re-enter your full name\n");
            }
        }

        while (true) {
            System.out.print("Email name: ");
            email = scanner.nextLine();
            if (email.length() > 0 && checkMail.validate(email)) {
                break;
            } else {
                System.out.println("\nYou have entered incorrect, please re-enter your email\n");
            }
        }

        Person person = new Person(user, fullName, email);
        pList.add(person);
        FileStream.writeFile(pList);
        clsDisplay();
        System.out.println("\nAdd new success...\n");
    }

    public void displayPerson() {
        pList = FileStream.readFile();
        int count = 1;
        for (Person p : pList) {
            System.out.format("| %-2s| %-11s | %-20s | %-21s |\n", count++, p.getUserName(), p.getFullName(), p.getEmail());
        }
    }

    // user, name, email, delete, edit, export
    public void smartSearch(String user, String name, String emailAddr, boolean del, boolean edit, boolean exportCSV) {
        UserInterface ui = new UserInterface();
        int countA = 1;
        pList = FileStream.readFile();
        int countB = pList.size();

        for (Person p : pList) {
            String userName = StringValid.valid(p.getUserName());
            String fullName = StringValid.valid(p.getFullName());
            String email = StringValid.valid(p.getEmail());

            if (user == null && name == null && emailAddr == null) {
                System.out.format("| %-2s| %-11s | %-20s | %-21s |\n", countA++, userName, fullName, email);
            }
            if (name == null && email == null || userName.contains(user) || userName.toLowerCase().contains(user)) {
                System.out.format("| %-2s| %-11s | %-20s | %-21s |\n", countA++, userName, fullName, email);

                if (del == true) {
                    ui.line();
                    System.out.print( "| Delete : [" + user + "] (yes/no): ");
                    String yesno = scanner.nextLine();
                    if (yesno.equals("yes") || yesno.equals("y")) {
                        pList.remove(p);
                        FileStream.writeFile(pList);
                        break;
                    } else if (yesno.equals("no") || yesno.equals("n")) {
                        break;
                    }
                }
                if (edit == true) {
                    ui.line();
                    System.out.print("| Edit: [" + user + "] (yes/no): ");
                    String yn = scanner.nextLine();
                    if (yn.equals("yes") || yn.equals("y")) {
                        pList.remove(p);
                        FileStream.writeFile(pList);
                        addPerson();
                        break;
                    }
                    else if(yn.equals("no") || yn.equals("n")){
                        break;
                    }
                }
                if(exportCSV == true){
                    ui.line();
                    System.out.print("| Do you want to export [" + user + "] ? (yes/no): ");
                    String yne = scanner.nextLine();
                    if (yne.equals("yes") || yne.equals("y")){
                        System.out.print("| You want to write [" + user + "] to file ?: ");
                        String fileName = scanner.nextLine();
                        FileStream.exportCSV(new File(fileName + ".csv"), user.toLowerCase());
                        System.out.println("| User [" + user + "] has been saved to file [" + fileName.toString() + ".csv]");
                    }
                    if (yne.equals("yes") || yne.equals("y")){
                        break;
                    }
                }
            }
            if (user == null && email == null || fullName.contains(name) || fullName.toLowerCase().contains(name)) {
                System.out.format("| %-2s| %-11s | %-20s | %-21s |\n", countA++, userName, fullName, email);
            }
            if (user == null && name == null || email.contains(emailAddr) || email.toLowerCase().contains(emailAddr)) {
                System.out.format("| %-2s| %-11s | %-20s | %-21s |\n", countA++, userName, fullName, email);
            }
        }

        if (countA > 1) {
            if (del == false && edit == false) {
                ui.line();
            }
            if (del == true && countB > pList.size()) {
                System.out.println("== Delete Successfully!\n");
            }
            if (edit == true && countB == pList.size()) {
                System.out.println("== Edit Successful!\n");
            }
            System.out.print("\n");
        } else if (countA == 1) {
            if (user == "null" && emailAddr == "null") {
                ui.line();
                System.out.format("| %-13s  %2s %-19s %2s %-22s|\n", "Not found ", "[", name, "]", "name in data");
                ui.line();
                System.out.println("");
            }
            if (name == "null" && emailAddr == "null") {
                System.out.format("| %-13s  %2s %-19s %2s %-22s|\n", "Not found ", "[", user, "]", "user in data");
                ui.line();
                System.out.println("");
            }
            if (user == "null" && name == "null") {
                ui.line();
                System.out.format("| %-13s  %2s %-19s %2s %-22s|\n", "Not found ", "[", emailAddr, "]", "domain in data");
                ui.line();
                System.out.println("");
            }
        }
    }
}