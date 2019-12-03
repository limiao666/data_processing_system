package team_work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PutIntoMap {
	private HashMap<String,ArrayList<UserInfo>> item_to_user = new HashMap<String,ArrayList<UserInfo>>();
	private HashMap<String,ArrayList<UserInfo>> user_to_item = new HashMap<String,ArrayList<UserInfo>>();
	
	public HashMap<String, ArrayList<UserInfo>> getItem_to_user() {
		return item_to_user;
	}
	public void setItem_to_user(HashMap<String, ArrayList<UserInfo>> itemToUser) {
		item_to_user = itemToUser;
	}
	public HashMap<String, ArrayList<UserInfo>> getUser_to_item() {
		return user_to_item;
	}
	public void setUser_to_item(HashMap<String, ArrayList<UserInfo>> userToItem) {
		user_to_item = userToItem;
	}
	//putIntoUserToItem
	public void putIntoUserToItem(ArrayList<UserInfo> userInfoList){
		Set<String> userID_set = new HashSet<String>();
		UserInfo userInfo;
		//取出userID放入set中，然后通过set的特性排除重复
		for(int i=0;i<userInfoList.size();i++){
			userInfo = userInfoList.get(i);
			String userID = userInfo.getUserID();
			userID_set.add(userID);
		}
		//迭代获取userID_set中元素
		Iterator it = userID_set.iterator();
		while(it.hasNext()){
			String userID = (String) it.next();
			ArrayList<UserInfo> related_user_info = new ArrayList<UserInfo>();
			for(int i=0;i<userInfoList.size();i++){
				userInfo = userInfoList.get(i);
				if(userID.equals(userInfo.getUserID())){
					related_user_info.add(userInfo);
				}
			}
			user_to_item.put(userID, related_user_info);
		}
		
	}
	//putIntoItemToUser
	public void putIntoItemToUser(ArrayList<UserInfo> userInfoList,ArrayList<MovieInfo> movieInfoList){
		MovieInfo movieInfo;
		UserInfo userInfo;
		for(int i=0;i<movieInfoList.size();i++){
			movieInfo = movieInfoList.get(i);
			String movieID = movieInfo.getMoveID();
			//存放对应movieID的用户信息
			ArrayList<UserInfo> related_user_info = new ArrayList<UserInfo>();
			for(int j=0;j<userInfoList.size();j++){
				userInfo = userInfoList.get(j);
				if(movieID.equals(userInfo.getMoveID())){
					related_user_info.add(userInfo);
//					System.out.println("has");
//					break;
				}
				//System.out.println(" related_user_info"+related_user_info);
			}
			
			item_to_user.put(movieID, related_user_info);
			//System.out.println("related_user_info:"+related_user_info);
		}
		//System.out.println("item_to_user"+item_to_user);
	}
}
