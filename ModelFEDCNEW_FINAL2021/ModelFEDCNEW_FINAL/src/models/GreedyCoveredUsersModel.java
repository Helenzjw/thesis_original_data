package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objectives.EdgeServer;
import objectives.User;

public class GreedyCoveredUsersModel {
	
	private double mAllUsers;
	//2021
	private double mCost;
	private double mbenefitefficiency;
	private double mAllBenefits;
	private double mAllBenefit_square;
	private int[][] mUserBenefits;
	
	private double mFairness_index;
	private double mfairnessdegree; //make the original value = 0
	private double mfairness_efficiency;
	private List<Integer> mBenefitList;
	//
	private int mServersNumber;
	private int mBudget;

	private int[][] mAdjacencyMatrix;
	private int[][] mUserCovered;
	private List<User> mUsers;
	List<EdgeServer> mServers;
	
	private Map<Integer, Integer> mCoveredUsersMap;
	private List<Integer> mValidUserList;
	private List<Integer> mSelectedServerList;
	//private List<Integer> mBenefitList;
	
	public GreedyCoveredUsersModel(int serversNumber, double fairness_index, 
			int[][] adjacencyMatrix, int[][] userCovered, int[][] userBenefits, 
			List<User> users, List<EdgeServer> servers) {
		mServersNumber = serversNumber;
		mAdjacencyMatrix = adjacencyMatrix;
		mUserCovered = userCovered;
		mUsers = users;
		mServers = servers;
		//2021
		mFairness_index = fairness_index;
		mUserBenefits = userBenefits;
		mBenefitList = new ArrayList<>();
		//
		mValidUserList = new ArrayList<>();
		mSelectedServerList = new ArrayList<>();
		//mBenefitList = new ArrayList<>();
		mCoveredUsersMap = new HashMap<>();//?
	}
	
	public void runGreedyCoveredUsers() {
		
		for (int i = 0; i < mServersNumber; i++) {
			mCoveredUsersMap.put(i, mServers.get(i).servedUsers.size());//What is this line mean?
		}
		
		//2021
		//ÕâÀï¸øfairness³õÊ¼»¯ÖµÎªÁã ºÍprivateÎªÁãÓÐÇø±ðÂð..ÎªÊ²Ã´Ñ­»·×ß²»³öÀ´£¿
		mfairnessdegree = 0;
		//
		
		/*while (mSelectedServerList.size() <= mBudget) {
			selectServerWithMaximumCoveredUsers();
		}*/
		
		//µ±²»ÊÇÈ«²¿ÓÃ»§±»¸²¸Ç»òÕßfairnessµÄdegreeÃ»ÓÐ´ïµ½indexÒªÇóÊ±£¬¾Í¼ÌÐøÑ¡ÔñÄÜcover ×î¶àÓÃ»§µÄserver
		while (mValidUserList.size() < mUsers.size()) {
			selectServerWithMaximumCoveredUsers();
			
			
		}	
		//2021
		//ÕâÀï¼ÆËãÒ»ÏÂÈ«²¿µÄcostºÍÈ«²¿µÄbenefitºÍÈ«²¿coverÓÃ»§µÄÊýÁ¿
		mCost = mSelectedServerList.size();
		mAllBenefits = calculateBenefits();//È«²¿ÓÃ»§benefitÖ®ºÍ
		mAllUsers = mValidUserList.size(); //¼ÆËãfairness degreeµÄ·ÖÄ¸
		mAllBenefit_square = calculate_single_Benefits_square(); //fairness degreeµÄ·ÖÄ¸
		
		//ÏÖÔÚ¼ÆËãfairness degree    ÕâÀïfairness_degreeºÍ m fairness degreeÒ»Ñù°É...
		//fairness_degree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);		
		mfairnessdegree = (mAllBenefits * mAllBenefits) / (mUsers.size() * mAllBenefit_square);	
		
		//¼ÆËãfairness efficiency
		//mfairness_efficiency = fairness_degree / mCost;		
		mfairness_efficiency = mfairnessdegree / mCost;
		//
	//	mAllUsers = mValidUserList.size();
	}
	
	private int selectServerWithMaximumCoveredUsers() {
		int maximumCoveredUsers = -1;
		int serverWithMaximumCoveredUsers = -1;
		for (int server : mCoveredUsersMap.keySet()) {
			int coveredUsers = mCoveredUsersMap.get(server);
			if (coveredUsers > maximumCoveredUsers) {
				maximumCoveredUsers = coveredUsers;
				serverWithMaximumCoveredUsers = server;
			}
		}
		
		/*for (int server : mCoveredUsersMap.keySet()) {
			int coveredUsers = mCoveredUsersMap.get(server);
			if (coveredUsers > maximumCoveredUsers) {
				maximumCoveredUsers = coveredUsers;
				serverWithMaximumCoveredUsers = server;
			}
		}*/
		
		//10.08
		 /*int max = 0;        
		 int serverWithMaximumCoveredUsers = 0;        
		 int value=0;        
		 for(int key : mCoveredUsersMap.keySet()){            
			 value = mCoveredUsersMap.get(key);            
			 if(max < value){                
				 max = value;                
				 serverWithMaximumCoveredUsers = key;            
				 }        
		 }*/
		 //mSelectedServerList.add(serverWithMaximumCoveredUsers);
		 //List<Integer> list = new ArrayList<Integer>();
		 //for (int server : mCoveredUsersMap.values()) { 
			// list.add(server);
		 //int maxcovereduserserver = Collections.max(list);
		 //int coveredUsers = mCoveredUsersMap.get(server);
		// }	
		
		//这里就是选择了cover数量最多的server 值是固定的 不是heuristic的解法
		mCoveredUsersMap.remove(serverWithMaximumCoveredUsers);
		mSelectedServerList.add(serverWithMaximumCoveredUsers);
		
		//这里是选好server 之后 把多覆盖到的用户添加到valid用户的列表里
		for (User user : mUsers) {
			if (user.nearEdgeServers.contains(serverWithMaximumCoveredUsers) && !mValidUserList.contains(user.id)) {
				mValidUserList.add(user.id);
			}
		}

		//这里是把选择到的server 的邻接server能cover 到的新的用户 也添加到选择道德用户列表里
		for (int i = 0; i < mServersNumber; i++) {
			if (mAdjacencyMatrix[i][serverWithMaximumCoveredUsers] == 1 || mAdjacencyMatrix[serverWithMaximumCoveredUsers][i] == 1) {
				// mConnectionsMap.remove(i);
				for (User user : mUsers) {
					if (user.nearEdgeServers.contains(i) && !mValidUserList.contains(user.id)) {
						mValidUserList.add(user.id);
					}
				}
			}
		}
		
		return serverWithMaximumCoveredUsers;
	}
	
	//2021
	//Õâ¸öº¯Êý¼ÆËãÁËÈ«²¿ÓÃ»§È«²¿µÄbenefitµÄºÍ È»ºó·µ»ØÁËÈ«²¿µÄbenefit
	private double calculateBenefits() {
		mBenefitList.clear();
		for (int i = 0; i < mUsers.size(); i++) {
			mBenefitList.add(0);
		}

		double benefits = 0;
		
		for (User user : mUsers) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerList.contains(server)) benefit = mUserBenefits[server][user.id];
				if (mBenefitList.get(user.id) < benefit) mBenefitList.set(user.id, benefit);
			}
		}
		
		//Ã¿Ò»¸öbenefit¶¼ÊÇÒ»¸öÓÃ»§µÄbenefit£¬È»ºóbenefitsÊÇÈ«²¿ÓÃ»§µÄbenefitÏà¼Ó
		for (int benefit : mBenefitList) benefits = benefits + benefit;
		
		return benefits;
	}
	
	//½ÓÏÂÀ´Ó¦¸Ã¼ÆËãÃ¿¸öÓÃ»§µÄbenefit£¬È»ºó°Ñbenefit´æÔÚÊý×éÀï£¬×îºóÑ­»·Ã¿¸öÊý×ÖµÄÆ½·½ºÍÏà¼Ó×÷Îªfairness¼ÆËãµÄ·ÖÄ¸
	private double calculate_single_Benefits_square() {
		mBenefitList.clear();
		for (int i = 0; i < mUsers.size(); i++) {
			mBenefitList.add(0);
		}

		double benefits = 0;
		
		for (User user : mUsers) {
			for (int server : user.nearEdgeServers) {
				int benefit = 1;
				if (mSelectedServerList.contains(server)) benefit = mUserBenefits[server][user.id];
				if (mBenefitList.get(user.id) < benefit) mBenefitList.set(user.id, benefit);
			}
		}
		
		//ÕâÀï¼ÆËãbenefitsÊÇÖ¸Ã¿Ò»¸öÓÃ»§µÄbenefitµÄÆ½·½ºÍ,ÕâÑùµÄ»°¿ÉÒÔ·µ»Øbenefits
		for (int benefit : mBenefitList) benefits = benefits + (benefit * benefit);
		
		return benefits;
	}
	//
	
	public double getAllUsers() {
		return mAllUsers;
	}
	
	public double getcost() {
		return mCost;
	}
	
	public double getfairness_degree() {
		return mfairnessdegree;
	}
	
	public double getfairness_efficiency() {
		return mfairness_efficiency;
	}
	
	public double getallbenefits() {
		return mAllBenefits = mAllBenefits/mValidUserList.size();
	}
	
	public double getbenefit_efficiency() {
		return mbenefitefficiency = mAllBenefits/mCost;
	}
}
