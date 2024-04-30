package application;

public class SessionHandler {
   public static String studentID;
   public static String currentLoanRecieversID;
   public static String borrowedDetails;

    public static String getBorrowedDetails() {
        return borrowedDetails;
    }

    public static void setBorrowedDetails(String borrowedDetails) {
        SessionHandler.borrowedDetails = borrowedDetails;
    }

    public static String getStudentID() {
        return studentID;
    }

    public static void setStudentID(String studentID) {
        SessionHandler.studentID = studentID;
    }

    public static String getCurrentLoanRecieversID() {
        return currentLoanRecieversID;
    }

    public static void setCurrentLoanRecieversID(String currentLoanRecieversID) {
        SessionHandler.currentLoanRecieversID = currentLoanRecieversID;
    }

    public static String getSession() {
        return studentID;
    }

    public static void setSession(String session) {
        studentID=session;
    }
}
