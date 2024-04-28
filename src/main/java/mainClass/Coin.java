package mainClass;

public class Coin {

    private int value;
    private String studentID;
public  Coin()
{

}

    @Override
    public String toString() {
        return "Coin{" +
                "value=" + value +
                ", studentID='" + studentID + '\'' +
                '}';
    }

    public Coin(int value, String studentID) {
        this.value = value;
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
