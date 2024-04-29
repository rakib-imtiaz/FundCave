package mainClass;

import java.security.PublicKey;

public class Account {

    String accountID;
    String studentID;
   double balance;

   public Account()
   {

   }

    public Account(String accountID, String studentID, double balance) {
        this.accountID = accountID;
        this.studentID = studentID;
        this.balance = balance;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", StudentID='" + studentID + '\'' +
                ", balance=" + balance +
                '}';
    }


}
