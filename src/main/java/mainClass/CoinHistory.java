package mainClass;

public class CoinHistory {
String studentID;
int givenLoan;
int takenLoan;

    public CoinHistory(String studentId, int givenLoan, int takenLoan) {
        this.studentID = studentId;
        this.givenLoan = givenLoan;
        this.takenLoan = takenLoan;
    }

    public CoinHistory() {

    }

    public String getStudentId() {
        return studentID;
    }

    public void setStudentId(String studentId) {
        this.studentID = studentId;
    }

    public int getGivenLoan() {
        return givenLoan;
    }

    public void setGivenLoan(int givenLoan) {
        this.givenLoan = givenLoan;
    }

    public int getTakenLoan() {
        return takenLoan;
    }

    public void setTakenLoan(int takenLoan) {
        this.takenLoan = takenLoan;
    }


    @Override
    public String toString() {
        return "CoinHistory{" +
                "studentId='" + studentID + '\'' +
                ", givenLoan=" + givenLoan +
                ", takenLoan=" + takenLoan +
                '}';
    }
}
