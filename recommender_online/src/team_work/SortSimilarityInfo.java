package team_work;

public class SortSimilarityInfo implements Comparable{
	private String MoveID;
	private String MoveInfo;
	private double similarity;
	public String getMoveID() {
		return MoveID;
	}
	public void setMoveID(String moveID) {
		MoveID = moveID;
	}
	public String getMoveInfo() {
		return MoveInfo;
	}
	public void setMoveInfo(String moveInfo) {
		MoveInfo = moveInfo;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	@Override
	public String toString() {
		return "SortSimilarityInfo [MoveID=" + MoveID + ", MoveInfo="
				+ MoveInfo + ", similarity=" + similarity + "]";
	}
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		SortSimilarityInfo s=(SortSimilarityInfo)o;
		if(s.similarity>this.similarity){
			return 1;
		}else{
			return -1;
		}
	}
	
}
