package Controllers;

import Models.Person;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by TOAN on 12/27/2014.
 */
public class FileStream {
    final static File fileName = new File("data.csv");

    public static ArrayList<Person> readFile() {
        String line = "";
        String splitBy = ",";
        ArrayList<Person> pList = new ArrayList<Person>();

        try {
            // Đẩy nội dung file vào br
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // while
            while ((line = br.readLine()) != null) {
                String[] result = line.split(splitBy);
                String user = StringValid.valid(result[0]);
                String name = StringValid.valid(result[1]);
                String email = StringValid.valid(result[2]);
                Person person = new Person(user, name, email);
                pList.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pList;
    }

    public static void writeFile(ArrayList<Person> pList) {
        try {
            FileWriter bw = new FileWriter(fileName);

            for (Person p : pList) {
                bw.write(p.getUserName() + "," + p.getFullName() + "," + p.getEmail() + "\n");
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            //
        }
    }

    public static void exportCSV(File fileName, String userName) {
        ArrayList<Person> pList = null;
        pList = FileStream.readFile();

        try {
            FileWriter fw = new FileWriter(fileName.getPath(), true);
            for (Person p : pList) {
                if (p.getUserName().contains(userName) || p.getUserName().toLowerCase().contains(userName)) {
                    fw.write(p.getUserName() + "," + p.getFullName() + "," + p.getEmail() + "\n");
                } else {
                    //
                }
            }
            fw.flush();
            fw.close();

            if (!fileName.exists()){
                fileName.delete();
            }
        } catch (Exception e) {
            //
        }
    }

    public static void importCSV(File file) {
        try {
            File fileName = new File("data.csv");
            int sizeData = (int) fileName.length();

            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            FileWriter fw = new FileWriter(fileName.getPath(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                bw.write(result[0] + "," + result[1] + "," + result[2] + "\n");
            }

            bw.flush();
            bw.close();
            if (fileName.length() > sizeData){
                System.out.println("Import thành công!\n");
            }else{
                System.out.println("Không import được, file csv của bạn ko có dữ liệu...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}