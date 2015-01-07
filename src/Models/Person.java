package Models;


/**
 * Created by TOAN on 12/27/2014.
 */
public class Person {
    private String userName;
    private String fullName;
    private String email;

    public Person(String userName, String fullName, String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
    }

    public Person(){
        ///
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}