package team_work;

public class UserInfo {
	private String userID;
	private String moveID;
	private double ratings;
	private String timeStamp;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getMoveID() {
		return moveID;
	}
	public void setMoveID(String moveID) {
		this.moveID = moveID;
	}
	public double getRatings() {
		return ratings;
	}
	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "UserInfo [moveID=" + moveID + ", ratings=" + ratings
				+ ", timeStamp=" + timeStamp + ", userID=" + userID + "]";
	}
	
}
