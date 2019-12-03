package team_work;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//执行读取文件并切割放入对象属性封装于ArrayList中
		FileIOMain io = new FileIOMain();
		try {
			io.readFile("/Users/crystal/Downloads/recommender_homework-master/recommender_online/recommender_system_data/ratings_train_test.txt",0);
			io.readFile("/Users/crystal/Downloads/recommender_homework-master/recommender_online/recommender_system_data/movies.dat",1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取到切割好的用户与电影的ArrayList
		ArrayList<MovieInfo> movieInfoList = io.getMovieInfoList();
		ArrayList<UserInfo> userInfoList = io.getUserInfoList();
		//进行映射item_to_user和user_to_item
		PutIntoMap putIntoMap = new PutIntoMap();
		putIntoMap.putIntoItemToUser(userInfoList, movieInfoList);
		putIntoMap.putIntoUserToItem(userInfoList);
		//获取到映射好的两个hashMap
		HashMap<String,ArrayList<UserInfo>> item_to_user = putIntoMap.getItem_to_user();
		HashMap<String,ArrayList<UserInfo>> user_to_item = putIntoMap.getUser_to_item();
		//打印出与movieID的邻居
		Main main = new Main();
		Scanner in = new Scanner(System.in);
		System.out.println("inputMovieID:");
		String InputID = in.next();
		System.out.println("================("+InputID+")的邻居==================");
		main.getNeighborSimilarityArrayList(InputID,item_to_user,user_to_item,movieInfoList);
//		for(int i=0;i<movieInfoList.size();i++){
//			MovieInfo movieInfo = movieInfoList.get(i);
//			System.out.println("================("+movieInfo.getMoveID()+")的邻居==================");
			//获取到邻居的相似度
//			main.getNeighborSimilarityArrayList(movieInfo.getMoveID(),item_to_user,user_to_item,movieInfoList);
			//排序
			//Collections.sort(sortSimilarityList,comparator);  
			//for(i=0;i<sortSimilarityList.size();i++)
//			if(sortSimilarityList.size()>0){
//				main.printSimilarity(sortSimilarityList);
//			}
//		}
		
		
		//System.out.println("item_to_user:"+item_to_user);
		//获取对应编号为1和25电影的评分列表
//		ArrayList<Double> firstMovieRatingsList = main.getRatingsArrayList("1258",item_to_user);
//		ArrayList<Double> secondMovieRatingsList = main.getRatingsArrayList("2389",item_to_user);
		//System.out.println("tttttt"+firstMovieRatingsList+","+secondMovieRatingsList);
		//打印出两部电影编号为1与编号为25的相似度
		//double similarity = main.sim(firstMovieRatingsList, secondMovieRatingsList);
//		System.out.println("================打印出movieID=1258与movieID=2389的相似度================");
//		System.out.println(similarity);
	}
	//打印出相似度
	
	public ArrayList<Double> getRatingsArrayList(String movieID,HashMap<String,ArrayList<UserInfo>> item_to_user){
		//存放评分的链表
		ArrayList<Double> ratingsList = new ArrayList<Double>();
		//存放看过movieID的用户链表
		ArrayList<UserInfo> userInfoList = item_to_user.get(movieID);
		//System.out.println("item_to_user.get(movieID):"+item_to_user.get(movieID));
		for(int i=0;i<userInfoList.size();i++){
			UserInfo userInfo = userInfoList.get(i);
			double rating = userInfo.getRatings();
			//System.out.println("rating:"+rating);
			ratingsList.add(rating);
		}
		return ratingsList;
	}
	//计算相似度
	public double sim(ArrayList va, ArrayList vb)
	{
		// 如果向量维度不相等，则不能计算，函数退出
		if (va.size()>vb.size())
		{
			int temp = va.size()-vb.size();
			for(int i=0;i<temp;i++){
				vb.add(0);
			}
		}else if(va.size()<vb.size()){
			int temp = vb.size()-va.size();
			for(int i=0;i<temp;i++){
				va.add(0);
			}
		}
		
		int size = va.size();
		double simVal = 0;
		
		//sim(va,vb) = (va * vb) / (|va| * |vb|)
		// 分子 = va.get(0)*vb.get(0) + va.get(1)*vb.get(1) +...+ va.get(size - 1)*vb.get(size - 1)
		// 分母 = va的模 * vb的模 = sqrt((va.get(0))的平方 + (va.get(1))的平方 + ... + va.get(size - 1)的平方) * sqrt((vb.get(0))的平方 + (vb.get(1))的平方 + ... + vb.get(size - 1)的平方)
		double num = 0;// numerator分子
		double den = 1;// denominator分母
		double powa_sum = 0;//va的平方和
		double powb_sum = 0;//vb的平方和
		for(int i=0;i<size;i++){
			double a = Double.parseDouble(va.get(i).toString());
			double b = Double.parseDouble(vb.get(i).toString());
//			System.out.println("aaaaaaa:"+a);
//			System.out.println("bbbbbbb:"+b);
			num = num + a*b;
			powa_sum = powa_sum + (double) Math.pow(a,2);
			powb_sum = powb_sum + (double) Math.pow(b,2);
		}
		double sqrta = (double) Math.sqrt(powa_sum);//求平方根
		double sqrtb = (double) Math.sqrt(powb_sum);//求平方根
		den = sqrta*sqrtb;
//		System.out.println("den:"+den);
//		System.out.println("num:"+num);
		simVal = num / den;
		
		return simVal;
	}
	public void getNeighborSimilarityArrayList(String str,
			HashMap<String,ArrayList<UserInfo>> item_to_user,
			HashMap<String,ArrayList<UserInfo>> user_to_item,
			ArrayList<MovieInfo> movieInfoList){
		//存放相似度封装成的对象，进行后续排序
		ArrayList<SortSimilarityInfo> sortSimilarityList = new ArrayList<SortSimilarityInfo>();
		//用来存放邻居的movieID
		Set<String> movieSet = new HashSet<String>();
		//存放取出对应movieID的用户列表
		ArrayList<UserInfo> userInfoList = item_to_user.get(str);
		//遍历列表userInfoList
		for(int i=0;i<userInfoList.size();i++){
			UserInfo userInfo = userInfoList.get(i);
			String userID = userInfo.getUserID();
			//从user_to_item获取看过某个movieID的用户列表
			ArrayList<UserInfo> userList = user_to_item.get(userID);
			//取出每位用户看过的电影，放入movieSet中
			for(int j=0;j<userList.size();j++){
				
				UserInfo user = userList.get(j);
				String movieID = user.getMoveID();
				if(!movieID.equals(str)){
					//获取到邻居电影的用户列表
					ArrayList<UserInfo> nbmoveIDUserList = item_to_user.get(movieID);
					//排除只有一个用户看过的电影
					if(nbmoveIDUserList.size()!=1){
						movieSet.add(movieID);
					}
				}
			}
		}
		
		Iterator it = movieSet.iterator();
		while(it.hasNext()){
			String movieID = (String) it.next();
			//从movieInfoList中遍历出相邻的movieID信息打印出来
			for(int j=0;j<movieInfoList.size();j++){
				MovieInfo movieInfo = movieInfoList.get(j);
				if(movieID.equals(movieInfo.getMoveID())){
					ArrayList<Double> firstMovieRatingsList = getRatingsArrayList(str,item_to_user);
					ArrayList<Double> secondMovieRatingsList = getRatingsArrayList(movieID,item_to_user);
					double similarity = sim(firstMovieRatingsList, secondMovieRatingsList);
					//System.out.println("电影编号："+movieInfo.getMoveID()+" 电影信息："+movieInfo.getMoveInfo()+" 与"+str+"的相似度："+similarity);
					//将每个邻居封装成SortSimilarityInfo对象
					SortSimilarityInfo sortSimilarityInfo = new SortSimilarityInfo();
					sortSimilarityInfo.setMoveID(movieInfo.getMoveID());
					sortSimilarityInfo.setMoveInfo(movieInfo.getMoveInfo());
					sortSimilarityInfo.setSimilarity(similarity);
					//创建ArrayList<SortSimilarityInfo>用于后续存放sortSimilarityInfo进行排序
					//sortSimilarityList = new ArrayList<SortSimilarityInfo>();
					sortSimilarityList.add(sortSimilarityInfo);
					
				}
			}
		}
		//排序
		Collections.sort(sortSimilarityList);
//		for(int i=0;i<sortSimilarityList.size();i++){
//			SortSimilarityInfo tempSimilarity;
//			SortSimilarityInfo sortSimilarityInfo1 = sortSimilarityList.get(i);
//			double similarity1 = sortSimilarityInfo1.getSimilarity();
//			//System.out.println(similarity1);
//			for(int j=i+1;j<sortSimilarityList.size();j++){
//				SortSimilarityInfo sortSimilarityInfo2 = sortSimilarityList.get(j);
//				double similarity2 = sortSimilarityInfo2.getSimilarity();
//				if(similarity2>similarity1){
//					tempSimilarity = sortSimilarityInfo2;
////					sortSimilarityList.add(j,sortSimilarityInfo1);
////					sortSimilarityList.remove(j+1);
////					sortSimilarityList.add(i, tempSimilarity);
////					sortSimilarityList.remove(i+1);
//					sortSimilarityList.remove(j);
//					sortSimilarityList.add(i,tempSimilarity);
//				}
//			}
//		}
		printSimilarity(sortSimilarityList);
		
	}
	public void printSimilarity(ArrayList<SortSimilarityInfo> sortSimilarityList){
		//打印出前15个相似度
		System.out.println("++++++++++++++++++++++有"+sortSimilarityList.size()+"位邻居,打印前15个+++++++++++++++++++++++++");
		if(sortSimilarityList.size()<15){
			for(int i=0;i<sortSimilarityList.size();i++){
				SortSimilarityInfo sortSimilarityInfo = sortSimilarityList.get(i);
				System.out.println(sortSimilarityInfo.getMoveID()+" "+sortSimilarityInfo.getMoveInfo()+" "+sortSimilarityInfo.getSimilarity());
			}
		}else{
			for(int i=0;i<15;i++){
				SortSimilarityInfo sortSimilarityInfo = sortSimilarityList.get(i);
				System.out.println(sortSimilarityInfo.getMoveID()+" "+sortSimilarityInfo.getMoveInfo()+" "+sortSimilarityInfo.getSimilarity());
			}
		}
	}
}
