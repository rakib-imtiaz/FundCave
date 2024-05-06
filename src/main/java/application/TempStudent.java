package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainClass.Account;
import mainClass.Coin;
import mainClass.Student;
import mainClass.Transaction;

import java.util.ArrayList;

public class TempStudent{
    private String name;
    private String email;
    private String password;
    private String studentID;
    private String anonymousID;
    private String address;
    private double balance;
    private int coin;

   public static ArrayList<TempStudent> populatedDataForAdmin = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getAnonymousID() {
        return anonymousID;
    }

    public void setAnonymousID(String anonymousID) {
        this.anonymousID = anonymousID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public static void populateData() {

        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
        ArrayList<Student> studentArrayList = DataBaseManager.getStudentArrayList();
        ArrayList<Coin> coinArrayList = DataBaseManager.getCoinArrayList();
        ArrayList<Account> accountArrayList = DataBaseManager.getAccountArraylist();

        // Assuming Student, Coin, and Account classes have appropriate getters

        for (int i = 0; i < studentArrayList.size(); i++) {
            TempStudent tempStudent = new TempStudent();
            tempStudent.setName(studentArrayList.get(i).getName());
            tempStudent.setEmail(studentArrayList.get(i).getEmail());
            tempStudent.setPassword(studentArrayList.get(i).getPassword());
            tempStudent.setStudentID(studentArrayList.get(i).getStudentID());
            tempStudent.setAddress(studentArrayList.get(i).getAddress());
            tempStudent.setAnonymousID(studentArrayList.get(i).getA_password());

            // Assuming the StudentID is unique and present in both Coin and Account lists
            for (Coin coin : coinArrayList) {
                if (coin.getStudentID().equals(studentArrayList.get(i).getStudentID())) {
                    tempStudent.setCoin(coin.getValue());
                    break;
                }
            }

            for (Account account : accountArrayList) {
                if (account.getStudentID().equals(studentArrayList.get(i).getStudentID())) {
                    tempStudent.setBalance(account.getBalance());
                    break;
                }
            }

            populatedDataForAdmin.add(tempStudent);
        }

        // Now populatedDataForAdmin contains all the necessary data for admin use
        DataBaseManager.closeConnection();
    }

}