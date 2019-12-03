package team_work;
public class MovieInfo {
	private String moveID;
	private String moveInfo;
	public String getMoveID() {
		return moveID;
	}
	public void setMoveID(String moveID) {
		this.moveID = moveID;
	}
	public String getMoveInfo() {
		return moveInfo;
	}
	public void setMoveInfo(String moveInfo) {
		this.moveInfo = moveInfo;
	}
	@Override
	public String toString() {
		return "MovieInfo [moveID=" + moveID + ", moveInfo=" + moveInfo + "]";
	}
	
}
