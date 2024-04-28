package mainClass;

import java.util.Date;

public class Post {

	String postID;
	String userID;
	String content;
	Date time;

	public  Post()
	{

	}


	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Post(String postID, String userID, String content, Date time) {


		this.postID = postID;
		this.userID = userID;
		this.content = content;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Post{" +
				"postID='" + postID + '\'' +
				", userID='" + userID + '\'' +
				", content='" + content + '\'' +
				", time=" + time +
				'}';
	}
}
