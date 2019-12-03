package team_work;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIOMain {
	private ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>();
	private ArrayList<MovieInfo> movieInfoList = new ArrayList<MovieInfo>();
	
	public ArrayList<UserInfo> getUserInfoList() {
		return userInfoList;
	}
	public void setUserInfoList(ArrayList<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}
	public ArrayList<MovieInfo> getMovieInfoList() {
		return movieInfoList;
	}
	public void setMovieInfoList(ArrayList<MovieInfo> movieInfoList) {
		this.movieInfoList = movieInfoList;
	}
	public void readFile(String file_path,int flag) throws IOException
	{
		//flag=0要读的是user数据，flag=1要读的是movies数据
		File file;
		FileReader fr;
		BufferedReader br;
		file = new File(file_path);
		if (!file.exists()){
			System.out.println("\""+file_path+"\" does not exsit!");
			return;
		}
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		while(br.ready()){
			String lineText=null;
			lineText = br.readLine();
			if(flag==0){
				userInfoSplit(lineText);
			}else{
				movieInfoSplit(lineText);
			}
			//System.out.println(lineText);
		}
		br.close();
	}
	//对user数据进行切割放入userInfoList中
	public void userInfoSplit(String str){
		UserInfo userInfo = new UserInfo();
		String[] strArray = str.split("::");
		userInfo.setUserID(strArray[0]);
		userInfo.setMoveID(strArray[1]);
		userInfo.setRatings(Double.parseDouble(strArray[2]));
		userInfo.setTimeStamp(strArray[3]);
		userInfoList.add(userInfo);
	}
	//对movie数据进行切割放入movieInfoList中
	public void movieInfoSplit(String str){
		MovieInfo movieInfo = new MovieInfo();
		String[] strArray = str.split("::");
		//mevieInfo.setUserID(strArray[0]);
		movieInfo.setMoveID(strArray[0]);
		String beforID = "";
		for(int i=1;i<strArray.length;i++){
			beforID = beforID+strArray[i];
		}
		movieInfo.setMoveInfo(beforID);
		movieInfoList.add(movieInfo);
	}
	
//	public static void main(String[] args) throws IOException {
//		FileIOMain io = new FileIOMain();
//		io.readFile("d:/ratings_train.dat",0);
//	}

}
