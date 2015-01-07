package Views;

import Controllers.UserInterface;
import Models.Person;

import java.util.ArrayList;

/**
 * Created by TOAN on 12/27/2014.
 */
public class TestApp {
    static ArrayList<Person> pList = new ArrayList<Person>();
    static UserInterface uf = new UserInterface();

    public static void main(String[] args) {
        System.out.println(uf.validString("   Nguyen         Quoc Toan       "));
    }
}
