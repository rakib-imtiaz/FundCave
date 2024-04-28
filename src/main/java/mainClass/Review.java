package mainClass;

import java.util.ArrayList;

public class Review {
	private ArrayList<Integer> reviewArrayList= new ArrayList<>();  // for example  4 ,4.5. 3.2 etc
	private String studentID;


	public Review(){

	}

	public Review(ArrayList<Integer> reviewArrayList, String studentID) {
		this.reviewArrayList = reviewArrayList;
		this.studentID = studentID;
	}

	public ArrayList<Integer> getReview()
	{
		return reviewArrayList;

	}
	public void setReview(int reviewNumber)
	{
		reviewArrayList.add(reviewNumber);
	}



	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}


	@Override
	public String toString() {
		return "Review{" +
				"reviewArrayList=" + reviewArrayList +
				", studentID='" + studentID + '\'' +
				'}';
	}
}
