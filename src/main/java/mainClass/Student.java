package mainClass;

import javafx.scene.image.Image;

public class Student {

	private String name;
	private String email;
	private String password;
	private String studentID;
	private String a_password;
	private Image profile_picture;
	private String address;

	public Student(String name, String email, String password, String studentID, String a_password, Image profile_picture, String address) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.studentID = studentID;
		this.a_password = a_password;
		this.profile_picture = profile_picture;
		this.address = address;
	}


	public Student()
	{

	}
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

	public String getA_password() {
		return a_password;
	}

	public void setA_password(String a_password) {
		this.a_password = a_password;
	}

	public Image getProfile_picture() {
		return profile_picture;
	}

	public void setProfile_picture(Image profile_picture) {
		this.profile_picture = profile_picture;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", studentID='" + studentID + '\'' +
				", a_password='" + a_password + '\'' +
				", profile_picture=" + profile_picture +
				", address='" + address + '\'' +
				'}';
	}
}
